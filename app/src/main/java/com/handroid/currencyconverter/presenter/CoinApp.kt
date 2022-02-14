package com.handroid.currencyconverter.presenter

import android.app.Application
import android.util.Log
import android.widget.Toast
import androidx.work.Configuration
import com.google.firebase.ktx.Firebase
import com.google.firebase.remoteconfig.ktx.remoteConfig
import com.google.firebase.remoteconfig.ktx.remoteConfigSettings
import com.handroid.currencyconverter.R
import com.handroid.currencyconverter.data.workers.AppWorkerFactory
import com.handroid.currencyconverter.di.component.DaggerApplicationComponent
import com.handroid.currencyconverter.presenter.ui.fragment.CoinListFragment


import javax.inject.Inject

class CoinApp : Application(), Configuration.Provider {

    @Inject
    lateinit var workerFactory: AppWorkerFactory

    val component by lazy {
        DaggerApplicationComponent.factory().create(this)
    }

    private val remoteConfig by lazy {
        Firebase.remoteConfig
    }

    override fun onCreate() {
        component.inject(this)
        super.onCreate()
        coinConfig()
    }

    override fun getWorkManagerConfiguration(): Configuration {
        return Configuration.Builder()
            .setWorkerFactory(
                workerFactory
            )
            .build()
    }

    private fun coinConfig() {
        val configSettings = remoteConfigSettings {
            minimumFetchIntervalInSeconds = 3600
        }
        with(remoteConfig) {
            setConfigSettingsAsync(configSettings)
            setDefaultsAsync(R.xml.show_default_crypto)
            getValueRemoteConfig()
        }
    }
    private fun getValueRemoteConfig() {
        remoteConfig.fetchAndActivate()
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val updated = task.result
                    Log.d(TAG, "Config params updated: $updated")
                    Toast.makeText(
                        this, "Fetch and activate succeeded",
                        Toast.LENGTH_SHORT
                    ).show()
                    //TODO()
                } else {
                    Toast.makeText(
                        this, "Fetch failed",
                        Toast.LENGTH_SHORT
                    ).show()
                    //TODO()
                    val setFromSymbols = remoteConfig.getString(SHOW_DEFAULT_CRYPTO)
                }
            }
    }
    companion object{
        const val TAG = "CoinApp"
        const val SHOW_DEFAULT_CRYPTO = "show_default_crypto"
        const val ON_SHOW_CONFIG = "on_show_config"
    }
}