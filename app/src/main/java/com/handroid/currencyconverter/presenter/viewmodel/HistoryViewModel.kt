package com.handroid.currencyconverter.presenter.viewmodel

import androidx.lifecycle.ViewModel
import com.handroid.currencyconverter.di.annotation.ApplicationScope
import com.handroid.currencyconverter.domain.usecase.GetHistoryPerDayUseCase
import com.handroid.currencyconverter.domain.usecase.GetHistoryPerMonthUseCase
import com.handroid.currencyconverter.domain.usecase.GetHistoryPerWeekUseCase
import com.handroid.currencyconverter.domain.usecase.LoadHistoryMonthUseCase
import javax.inject.Inject

@ApplicationScope
class HistoryViewModel @Inject constructor(
    private val getHistoryPerMonthUseCase: GetHistoryPerMonthUseCase,
    private val getHistoryPerWeekUseCase: GetHistoryPerWeekUseCase,
    private val getHistoryPerDayUseCase: GetHistoryPerDayUseCase,
    private val loadHistoryMonthUseCase: LoadHistoryMonthUseCase
) : ViewModel() {

    val historyMonth = getHistoryPerMonthUseCase()

    fun getHistoryInfoMonth(time: Int) = getHistoryPerDayUseCase(time)

    init {
        loadHistoryMonthUseCase()

    }
}