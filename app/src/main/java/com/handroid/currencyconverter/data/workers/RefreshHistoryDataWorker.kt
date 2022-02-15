package com.handroid.currencyconverter.data.workers

import android.content.Context
import androidx.lifecycle.Transformations
import androidx.work.*
import com.handroid.currencyconverter.data.database.HistoryInfoDao
import com.handroid.currencyconverter.data.database.model.HistoryMapContainerModel
import com.handroid.currencyconverter.data.mapper.CoinMapper
import com.handroid.currencyconverter.data.network.ApiService
import com.handroid.currencyconverter.data.network.dto.history.HistoryInfoListDto
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
                val fSyms = CoinNameListDto().names.toString()
                val dbHistoryList = mutableListOf<HistoryMapContainerModel>()
                for (request in fSyms) {
                    val historyByMonth = api.getCoinInfoPerDay(fSym = fSyms, limit = 30)
                    val historyInfoDtoList = mapper.mapJsonToListHistoryInfo(historyByMonth)
                    historyInfoDtoList.map {
                        dbHistoryList.add(
                            HistoryMapContainerModel(
                                fromSymbols = fSyms,
                                historyInfoModel =  it.history.historyList.map {
                                    mapper.mapHistoryDtoToModel(it)
                                }
                            )
                        )
                    }
                }
                historyInfoDao.insertHistoryList(dbHistoryList.toList())
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