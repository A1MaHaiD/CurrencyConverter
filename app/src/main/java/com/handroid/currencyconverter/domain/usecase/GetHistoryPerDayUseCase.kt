package com.handroid.currencyconverter.domain.usecase

import com.handroid.currencyconverter.data.database.model.HistoryInfoModel
import com.handroid.currencyconverter.domain.CoinRepository
import com.handroid.currencyconverter.domain.entity.HistoryInfoEntity
import javax.inject.Inject

class GetHistoryPerDayUseCase @Inject constructor(
    private val repository: CoinRepository
) {
    operator fun invoke(
        fromSymbols: String,
        historyInfoModel: HistoryInfoModel
    ) = repository.getHistoryPerDay(fromSymbols, historyInfoModel)
}