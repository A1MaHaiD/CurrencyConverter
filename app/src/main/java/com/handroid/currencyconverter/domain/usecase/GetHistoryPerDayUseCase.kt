package com.handroid.currencyconverter.domain.usecase

import com.handroid.currencyconverter.domain.CoinRepository

class GetHistoryPerDayUseCase(
    private val repository: CoinRepository
) {
    operator fun invoke(time:Int) = repository.getHistoryPerDay(time)
}