package com.handroid.currencyconverter.domain

import androidx.lifecycle.LiveData
import com.handroid.currencyconverter.data.database.model.HistoryInfoModel
import com.handroid.currencyconverter.domain.entity.CoinInfoEntity
import com.handroid.currencyconverter.domain.entity.HistoryInfoEntity

interface CoinRepository {

    fun getCoinList(): LiveData<List<CoinInfoEntity>>

    fun getCoinItem(fromSymbol: String): LiveData<CoinInfoEntity>

    fun loadCoinDate()

    fun loadHistoryMonth()

    fun getHistoryPerDay(
        fromSymbols: String,
        historyInfoModel: HistoryInfoModel
    ): LiveData<HistoryInfoEntity>

    fun getHistoryPerWeek(fromSymbols: String): LiveData<List<HistoryInfoEntity>>

    fun getHistoryPerMonth(fromSymbols: String): LiveData<List<HistoryInfoEntity>>
}