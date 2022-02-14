package com.handroid.currencyconverter.di.module

import android.app.Application
import com.handroid.currencyconverter.data.database.AppCoinDatabase
import com.handroid.currencyconverter.data.database.AppHistoryDatabase
import com.handroid.currencyconverter.data.database.CoinInfoDao
import com.handroid.currencyconverter.data.database.HistoryInfoDao
import com.handroid.currencyconverter.data.network.ApiFactory
import com.handroid.currencyconverter.data.network.ApiService
import com.handroid.currencyconverter.data.repository.CoinRepositoryImpl
import com.handroid.currencyconverter.di.annotation.ApplicationScope
import com.handroid.currencyconverter.domain.CoinRepository
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
interface DataModule {

    @Binds
    @ApplicationScope
    fun bindCoinRepository(impl: CoinRepositoryImpl): CoinRepository

    companion object {
        @Provides
        @ApplicationScope
        fun provideCoinInfoDao(application: Application): CoinInfoDao {
            return AppCoinDatabase.getInstance(application).coinInfoDao()
        }

        @Provides
        @ApplicationScope
        fun provideHistoryInfoDao(application: Application): HistoryInfoDao {
            return AppHistoryDatabase.getInstance(application).historyInfoDao()
        }

        @Provides
        @ApplicationScope
        fun provideApiService(): ApiService {
            return ApiFactory.apiService
        }
    }
}
