package com.handroid.currencyconverter.data.workers

import android.content.Context
import androidx.work.*
import com.handroid.currencyconverter.data.database.HistoryInfoDao
import com.handroid.currencyconverter.data.mapper.CoinMapper
import com.handroid.currencyconverter.data.network.ApiService
import com.handroid.currencyconverter.data.network.dto.history.HistoryInfoDto
import com.handroid.currencyconverter.data.network.dto.namelist.CoinNameListDto
import kotlinx.coroutines.delay
import javax.inject.Inject

class RefreshHistoryDataWorker(
    context: Context,
    workerParameters: WorkerParameters,
    private val historyInfoDao: HistoryInfoDao,
    private val api: ApiService,
    private val mapper: CoinMapper
) : CoroutineWorker(context, workerParameters) {
    override suspend fun doWork(): Result {
        while (true) {
            try {
                val listHistoryByMonth = mutableListOf<List<HistoryInfoDto>>()
                val fSyms = CoinNameListDto().names.toString()
//                val fSyms = "BTC" //пізніше потрібно змінити на списко String
                val historyByMonth = api.getCoinInfoPerDay(fSym = fSyms, limit = 30)
                val historyInfoDtoList = mapper.mapContainerToListHistoryInfo(historyByMonth)
                for (i in fSyms) {
                    listHistoryByMonth.add(historyInfoDtoList)
                }
                val dbHistory = listHistoryByMonth.map {
                    it.map {
                        mapper.mapHistoryDtoToModel(it)
                    }
                }
                for (i in fSyms) {
                    dbHistory.map {
                        it.map {
                        }
                        historyInfoDao.insertHistoryList(it)
                    }
                }
            } catch (e: Exception) {
            }
            delay(5_000)
//            delay(2_160_000)
        }
    }

    companion object {
        const val NAME = "RefreshHistoryDataWorker"

        fun makeRequest(): OneTimeWorkRequest {
            return OneTimeWorkRequestBuilder<RefreshHistoryDataWorker>()
                .setConstraints(makeConstraints())
                .build()
        }

        private fun makeConstraints() = Constraints.Builder()
            .setRequiredNetworkType(NetworkType.CONNECTED)
            .build()
    }

    class Factory @Inject constructor(
        private val historyInfoDao: HistoryInfoDao,
        private val api: ApiService,
        private val mapper: CoinMapper
    ) : ChildWorkerFactory {
        override fun create(
            context: Context,
            workerParameters: WorkerParameters
        ): ListenableWorker {
            return RefreshHistoryDataWorker(
                context, workerParameters, historyInfoDao, api, mapper
            )
        }
    }
}