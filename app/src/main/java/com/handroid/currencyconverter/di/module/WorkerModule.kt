package com.handroid.currencyconverter.di.module

import com.handroid.currencyconverter.data.workers.ChildWorkerFactory
import com.handroid.currencyconverter.data.workers.RefreshCoinDataWorker
import com.handroid.currencyconverter.data.workers.RefreshHistoryDataWorker
import com.handroid.currencyconverter.di.annotation.WorkerKey
import dagger.Binds
import dagger.multibindings.IntoMap

interface WorkerModule {

    @Binds
    @IntoMap
    @WorkerKey(RefreshCoinDataWorker::class)
    fun bindRefreshCoinDataWorkerFactory(worker: RefreshCoinDataWorker.Factory): ChildWorkerFactory

    @Binds
    @IntoMap
    @WorkerKey(RefreshHistoryDataWorker::class)
    fun bindRefreshCoinDataWorkerFactory(worker: RefreshHistoryDataWorker.Factory): ChildWorkerFactory
}