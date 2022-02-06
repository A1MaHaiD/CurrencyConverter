package com.handroid.currencyconverter.domain.usecase

import com.handroid.currencyconverter.domain.CoinRepository

class GetHistoryPerMonthUseCase(
    private val repository: CoinRepository
) {
    operator fun invoke(limit: Int) = repository.getHistoryPerMonth(limit)
}