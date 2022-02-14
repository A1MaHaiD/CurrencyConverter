package com.handroid.currencyconverter.data.workers

import android.content.Context
import androidx.work.*
import com.handroid.currencyconverter.data.database.CoinInfoDao
import com.handroid.currencyconverter.data.mapper.CoinMapper
import com.handroid.currencyconverter.data.network.ApiService
import kotlinx.coroutines.delay
import javax.inject.Inject

class RefreshCoinDataWorker (
    context: Context,
    workerParameters: WorkerParameters,
    private val coinInfoDao: CoinInfoDao,
    private val api: ApiService,
    private val mapper: CoinMapper
) : CoroutineWorker(context, workerParameters) {
    override suspend fun doWork(): Result {
        while (true) {
            try {
                val topCoins = api.getTopCoinInfo(limit = 30)
                val fSyms = mapper.mapNameListToString(topCoins)
                val jsonContainer = api.getFullPriceList(fSyms = fSyms)
                val coinInfoDtoList = mapper.mapJsonToListCoinInfo(jsonContainer)
                val dbModelList = coinInfoDtoList.map { mapper.mapCoinDtoToModel(it) }
                coinInfoDao.insertPriceList(dbModelList)
            } catch (e: Exception) {
            }
            delay(10_000)
        }
    }

    companion object {
        const val NAME = "RefreshDataWorker"

        fun makeRequest(): OneTimeWorkRequest {
            return OneTimeWorkRequestBuilder<RefreshCoinDataWorker>()
//                .setConstraints(makeConstraints())
                .build()
        }

        private fun makeConstraints() = Constraints.Builder()
            .setRequiredNetworkType(NetworkType.CONNECTED)
            .build()
    }

    class Factory @Inject constructor(
        private val coinInfoDao: CoinInfoDao,
        private val api: ApiService,
        private val mapper: CoinMapper
    ):ChildWorkerFactory {
        override fun create(
            context: Context,
            workerParameters: WorkerParameters
        ): ListenableWorker {
            return RefreshCoinDataWorker(
                context, workerParameters, coinInfoDao, api, mapper
            )
        }
    }
}