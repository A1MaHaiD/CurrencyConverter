package com.handroid.currencyconverter.data.workers

import android.content.Context
import androidx.work.ListenableWorker
import androidx.work.WorkerFactory
import androidx.work.WorkerParameters
import javax.inject.Inject
import javax.inject.Provider

class AppWorkerFactory @Inject constructor(
    private val workerProviders: @JvmSuppressWildcards Map<Class<out ListenableWorker>, Provider<ChildWorkerFactory>>
) : WorkerFactory() {
    override fun createWorker(
        appContext: Context,
        workerClassName: String,
        workerParameters: WorkerParameters
    ): ListenableWorker? = when (workerClassName) {
        RefreshCoinDataWorker::class.qualifiedName -> {
            val childWorkerFactory = workerProviders[RefreshCoinDataWorker::class.java]?.get()
            childWorkerFactory?.create(appContext,workerParameters)
        }
        RefreshHistoryDataWorker::class.qualifiedName -> {
            val childWorkerFactory = workerProviders[RefreshHistoryDataWorker::class.java]?.get()
            childWorkerFactory?.create(appContext,workerParameters)
        }
        else -> {
            null
        }
    }
}