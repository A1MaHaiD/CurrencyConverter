package com.handroid.currencyconverter.domain

import androidx.lifecycle.LiveData
import com.handroid.currencyconverter.domain.entity.CoinInfoEntity
import com.handroid.currencyconverter.domain.entity.HistoryInfoEntity

interface CoinRepository {

    fun getCoinList(): LiveData<List<CoinInfoEntity>>

    fun getCoinItem(fromSymbol: String): LiveData<CoinInfoEntity>

    fun getHistoryPerMonth(limit:Int):LiveData<HistoryInfoEntity>

    fun getHistoryPerWeek(limit: Int):LiveData<HistoryInfoEntity>
}