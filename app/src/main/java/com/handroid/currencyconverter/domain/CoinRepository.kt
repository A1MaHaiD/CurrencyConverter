package com.handroid.currencyconverter.domain

import androidx.lifecycle.LiveData

interface CoinRepository {

    fun getCoinList(): LiveData<List<CoinEntity>>

    fun getCoinItem(fromSymbol: String): LiveData<CoinEntity>
}