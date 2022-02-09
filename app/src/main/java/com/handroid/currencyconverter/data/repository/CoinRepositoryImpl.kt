package com.handroid.currencyconverter.data.repository

import android.app.Application
import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.work.ExistingWorkPolicy
import androidx.work.WorkManager
import com.handroid.currencyconverter.CoinApp
import com.handroid.currencyconverter.data.database.CoinInfoDao
import com.handroid.currencyconverter.data.database.HistoryInfoDao
import com.handroid.currencyconverter.data.mapper.CoinMapper
import com.handroid.currencyconverter.data.workers.RefreshCoinDataWorker
import com.handroid.currencyconverter.data.workers.RefreshHistoryDataWorker
import com.handroid.currencyconverter.domain.CoinRepository
import com.handroid.currencyconverter.domain.entity.CoinInfoEntity
import com.handroid.currencyconverter.domain.entity.HistoryInfoEntity
import javax.inject.Inject

class CoinRepositoryImpl @Inject constructor(
    private val mapper: CoinMapper,
    private val coinInfoDao: CoinInfoDao,
    private val historyInfoDao: HistoryInfoDao,
    private val application: Application,
) : CoinRepository {

    override fun getCoinList(): LiveData<List<CoinInfoEntity>> {
        return Transformations.map(coinInfoDao.getPriceList()) {
            it.map {
                mapper.mapCoinModelToEntity(it)
            }
        }
    }

    override fun getCoinItem(fromSymbol: String): LiveData<CoinInfoEntity> {
        return Transformations.map(coinInfoDao.getFullCoinInfo(fromSymbol)) {
            mapper.mapCoinModelToEntity(it)
        }
    }

    override fun getHistoryPerDay(time: Int): LiveData<HistoryInfoEntity> {
        return Transformations.map(historyInfoDao.getHistoryPerDay(time)) {
            mapper.mapHistoryModelToEntity(it)
        }
    }

    override fun getHistoryPerMonth(): LiveData<List<HistoryInfoEntity>> {
        return Transformations.map(historyInfoDao.getHistoryPerPeriod()) {
            it.map {
                mapper.mapHistoryModelToEntity(it)
            }
        }
    }

    override fun getHistoryPerWeek(): LiveData<List<HistoryInfoEntity>> {
        return Transformations.map(historyInfoDao.getHistoryPerPeriod()) {
            it.subList(0, 7).map {
                mapper.mapHistoryModelToEntity(it)
            }
        }
    }

    override fun loadCoinDate() {
        val workManagerCoin = WorkManager.getInstance(application)
        workManagerCoin.enqueueUniqueWork(
            RefreshCoinDataWorker.NAME,
            ExistingWorkPolicy.REPLACE,
            RefreshCoinDataWorker.makeRequest()
        )
    }

    override fun loadHistoryMonth() {
        val workManagerHistory = WorkManager.getInstance(application)
        workManagerHistory.enqueueUniqueWork(
            RefreshHistoryDataWorker.NAME,
            ExistingWorkPolicy.REPLACE,
            RefreshHistoryDataWorker.makeRequest()
        )
    }
}