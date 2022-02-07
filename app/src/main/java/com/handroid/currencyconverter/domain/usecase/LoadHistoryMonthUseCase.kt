package com.handroid.currencyconverter.domain.usecase

import com.handroid.currencyconverter.domain.CoinRepository

class LoadHistoryMonthUseCase(
    private val repository: CoinRepository
) {
    suspend operator fun invoke() = repository.loadHistoryMonth()
}