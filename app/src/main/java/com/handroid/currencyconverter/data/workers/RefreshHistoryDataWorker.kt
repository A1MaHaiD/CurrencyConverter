package com.handroid.currencyconverter.data.workers

import android.content.Context
import androidx.work.*
import com.handroid.currencyconverter.data.database.HistoryInfoDao
import com.handroid.currencyconverter.data.database.model.HistoryInfoModel
import com.handroid.currencyconverter.data.mapper.CoinMapper
import com.handroid.currencyconverter.data.network.ApiService
import com.handroid.currencyconverter.data.network.dto.history.HistoryInfoDto
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
                val itList = mutableListOf<HistoryInfoModel>()
                val topCoins = api.getTopCoinInfo(limit = 30)
                val fSyms = mapper.mapNameListToIterationName(topCoins)
                for (fsym in fSyms) {
                    val listHistoryByMonth = mutableListOf<List<HistoryInfoDto>>()
                    val historyByMonth = api.getCoinInfoPerDay(fSym = fsym, limit = 30)
                    val historyInfoDtoList = mapper.mapContainerToListHistoryInfo(historyByMonth)
                    listHistoryByMonth.add(historyInfoDtoList)
                    val dbHistory = listHistoryByMonth.map {
                        it.map {
                            mapper.mapHistoryDtoToModel(it)
                        }
                    }
                    for (i in listHistoryByMonth) {
                        dbHistory.map {
                            it.map {
                                itList.add(it)
                            }
                        }
                    }
                }
                historyInfoDao.insertHistoryList(itList)
            } catch (e: Exception) {
            }
            delay(30_000)
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