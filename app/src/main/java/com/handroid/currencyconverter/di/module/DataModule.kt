package com.handroid.currencyconverter.di.module

import com.handroid.currencyconverter.data.database.AppDatabase
import com.handroid.currencyconverter.data.database.CoinInfoDao
import com.handroid.currencyconverter.data.database.HistoryInfoDao
import com.handroid.currencyconverter.data.network.ApiFactory
import com.handroid.currencyconverter.data.network.ApiService
import com.handroid.currencyconverter.di.annotation.ApplicationScope
import dagger.Binds

interface DataModule {

    @ApplicationScope
    @Binds
    fun bindApiService(impApi: ApiFactory): ApiService

    @ApplicationScope
    @Binds
    fun bindCoinInfoDao(impCoinDb: AppDatabase): CoinInfoDao

    @ApplicationScope
    @Binds
    fun bindHistoryInfoDao(impHistoryDb: AppDatabase): HistoryInfoDao
}