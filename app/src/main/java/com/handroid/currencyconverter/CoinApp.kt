package com.handroid.currencyconverter

import android.app.Application
import androidx.work.Configuration
import com.handroid.currencyconverter.data.workers.AppWorkerFactory
import com.handroid.currencyconverter.di.DaggerApplicationComponent
import javax.inject.Inject

class CoinApp : Application(), Configuration.Provider {

    @Inject
    lateinit var workerFactory: AppWorkerFactory

    val component by lazy {
        DaggerApplicationComponent.factory().create(this)
    }

    override fun onCreate() {
        component.inject(this)
        super.onCreate()
    }

    override fun getWorkManagerConfiguration(): Configuration {
        return Configuration.Builder()
            .setWorkerFactory(
                workerFactory
            )
            .build()
    }
}