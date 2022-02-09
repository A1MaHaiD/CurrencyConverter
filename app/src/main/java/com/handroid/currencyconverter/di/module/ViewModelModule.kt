package com.handroid.currencyconverter.di.module

import androidx.lifecycle.ViewModel
import com.handroid.currencyconverter.di.annotation.ViewModelKey
import com.handroid.currencyconverter.presenter.viewmodel.HistoryViewModel
import com.handroid.currencyconverter.presenter.viewmodel.CoinViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(CoinViewModel::class)
    fun bindCoinViewModel(viewModel : CoinViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(HistoryViewModel::class)
    fun bindCoinHistoryViewModel(viewModel : HistoryViewModel): ViewModel
}