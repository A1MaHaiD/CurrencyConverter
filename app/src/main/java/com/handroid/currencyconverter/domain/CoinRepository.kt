package com.handroid.currencyconverter.domain

import androidx.lifecycle.LiveData
import com.handroid.currencyconverter.domain.entity.CoinInfoEntity
import com.handroid.currencyconverter.domain.entity.HistoryInfoEntity

interface CoinRepository {

    fun getCoinList(): LiveData<List<CoinInfoEntity>>

    fun getCoinItem(fromSymbol: String): LiveData<CoinInfoEntity>

    fun loadCoinDate()

    fun loadHistoryMonth()

    fun getHistoryPerDay(time:Int): LiveData<HistoryInfoEntity>

    fun getHistoryPerMonth(): LiveData<List<HistoryInfoEntity>>

    fun getHistoryPerWeek(): LiveData<List<HistoryInfoEntity>>
}