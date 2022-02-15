package com.handroid.currencyconverter.domain.usecase

import com.handroid.currencyconverter.domain.CoinRepository
import javax.inject.Inject

class GetHistoryPerWeekUseCase @Inject constructor(
    private val repository: CoinRepository
) {
    operator fun invoke(fromSymbols: String) = repository.getHistoryPerWeek(fromSymbols)
}