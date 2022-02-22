package com.handroid.currencyconverter.di.component

import android.app.Application
import com.handroid.currencyconverter.di.annotation.ApplicationScope
import com.handroid.currencyconverter.di.module.DataModule
import com.handroid.currencyconverter.di.module.ViewModelModule
import com.handroid.currencyconverter.di.module.WorkerModule
import com.handroid.currencyconverter.CoinApp
import com.handroid.currencyconverter.presenter.ui.CoinActivity
import com.handroid.currencyconverter.presenter.ui.fragment.CoinDetailFragment
import com.handroid.currencyconverter.presenter.ui.fragment.CoinHistoryFragment
import com.handroid.currencyconverter.presenter.ui.fragment.CoinListFragment
import com.handroid.currencyconverter.presenter.ui.fragment.WelcomeFragment
import dagger.BindsInstance
import dagger.Component

@ApplicationScope
@Component(
    modules = [
        DataModule::class,
        ViewModelModule::class,
        WorkerModule::class
    ]
)
interface ApplicationComponent {

    fun inject(activity: CoinActivity)

    fun inject(fragment: WelcomeFragment)

    fun inject(fragment: CoinListFragment)

    fun inject(fragment: CoinHistoryFragment)

    fun inject(fragment: CoinDetailFragment)

    fun inject(application: CoinApp)

    @Component.Factory
    interface Factory {

        fun create(
            @BindsInstance application: Application
        ): ApplicationComponent
    }
}