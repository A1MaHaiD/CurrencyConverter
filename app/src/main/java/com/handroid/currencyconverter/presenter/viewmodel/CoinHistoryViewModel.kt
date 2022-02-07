package com.handroid.currencyconverter.presenter.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.handroid.currencyconverter.data.repository.CoinRepositoryImpl
import com.handroid.currencyconverter.domain.usecase.GetHistoryPerMonthUseCase
import com.handroid.currencyconverter.domain.usecase.GetHistoryPerWeekUseCase
import com.handroid.currencyconverter.domain.usecase.LoadHistoryWeekUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

class CoinHistoryViewModel @Inject constructor(
    private val repositoryImpl: CoinRepositoryImpl
) : ViewModel() {

    private val loadHistoryDataUseCase = LoadHistoryWeekUseCase(repositoryImpl)
    private val getHistoryPerMonthUseCase = GetHistoryPerMonthUseCase(repositoryImpl)
    private val getHistoryPerWeekUseCase = GetHistoryPerWeekUseCase(repositoryImpl)

    fun getDetailInfo(limit: Int) = getHistoryPerWeekUseCase(limit)

    init {
        viewModelScope.launch {
            repositoryImpl.loadHistoryWeek()
        }
    }
}