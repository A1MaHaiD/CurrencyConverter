package com.handroid.currencyconverter.data.repository

import androidx.lifecycle.LiveData
import com.handroid.currencyconverter.data.database.AppDatabase
import com.handroid.currencyconverter.data.network.ApiFactory
import com.handroid.currencyconverter.domain.CoinRepository
import com.handroid.currencyconverter.domain.entity.CoinInfoEntity
import com.handroid.currencyconverter.domain.entity.HistoryInfoEntity
import javax.inject.Inject

class CoinRepositoryImpl @Inject constructor(
    private val database: AppDatabase,
    private val api: ApiFactory
): CoinRepository {

    override fun getCoinList(): LiveData<List<CoinInfoEntity>> {
        TODO("Not yet implemented")
    }

    override fun getCoinItem(fromSymbol: String): LiveData<CoinInfoEntity> {
        TODO("Not yet implemented")
    }

    override fun getHistoryPerMonth(limit: Int): LiveData<HistoryInfoEntity> {
        TODO("Not yet implemented")
    }

    override fun getHistoryPerWeek(limit: Int): LiveData<HistoryInfoEntity> {
        TODO("Not yet implemented")
    }
}