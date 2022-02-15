package com.handroid.currencyconverter.presenter.viewmodel

import androidx.lifecycle.ViewModel
import com.handroid.currencyconverter.data.database.model.HistoryInfoModel
import com.handroid.currencyconverter.domain.usecase.GetHistoryPerDayUseCase
import com.handroid.currencyconverter.domain.usecase.GetHistoryPerMonthUseCase
import com.handroid.currencyconverter.domain.usecase.GetHistoryPerWeekUseCase
import com.handroid.currencyconverter.domain.usecase.LoadHistoryMonthUseCase
import javax.inject.Inject

class HistoryViewModel @Inject constructor(
    private val getHistoryPerMonthUseCase: GetHistoryPerMonthUseCase,
    private val getHistoryPerWeekUseCase: GetHistoryPerWeekUseCase,
    private val getHistoryPerDayUseCase: GetHistoryPerDayUseCase,
    private val loadHistoryMonthUseCase: LoadHistoryMonthUseCase
) : ViewModel() {

    fun getHistoryInfoPerDay(
        fromSymbols: String,
        historyInfoModel: HistoryInfoModel
    ) = getHistoryPerDayUseCase(fromSymbols, historyInfoModel)

    fun getHistoryInfoMonth(fromSymbols: String) = getHistoryPerMonthUseCase(fromSymbols)

    fun getHistoryInfoWeek(fromSymbols: String) = getHistoryPerWeekUseCase(fromSymbols)

    init {
        loadHistoryMonthUseCase()
    }
}