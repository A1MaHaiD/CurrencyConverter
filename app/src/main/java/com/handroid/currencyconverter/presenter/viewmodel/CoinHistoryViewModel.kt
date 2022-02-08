package com.handroid.currencyconverter.presenter.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.handroid.currencyconverter.domain.CoinRepository
import com.handroid.currencyconverter.domain.usecase.GetHistoryPerDayUseCase
import com.handroid.currencyconverter.domain.usecase.GetHistoryPerMonthUseCase
import com.handroid.currencyconverter.domain.usecase.GetHistoryPerWeekUseCase
import com.handroid.currencyconverter.domain.usecase.LoadHistoryMonthUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

class CoinHistoryViewModel @Inject constructor(
    private val repository: CoinRepository,
    private val loadHistoryMonthUseCase: LoadHistoryMonthUseCase,
    private val getHistoryPerMonthUseCase: GetHistoryPerMonthUseCase,
    private val getHistoryPerWeekUseCase: GetHistoryPerWeekUseCase,
    private val getHistoryPerDayUseCase: GetHistoryPerDayUseCase
) : ViewModel() {

    fun getHistoryInfoMonth() = getHistoryPerMonthUseCase()


    init {
        viewModelScope.launch {
            loadHistoryMonthUseCase.invoke()
        }
    }
}