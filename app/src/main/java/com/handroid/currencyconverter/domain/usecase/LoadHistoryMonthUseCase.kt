package com.handroid.currencyconverter.domain.usecase

import com.handroid.currencyconverter.domain.CoinRepository
import javax.inject.Inject

class LoadHistoryMonthUseCase @Inject constructor(
    private val repository: CoinRepository
) {
    operator fun invoke() = repository.loadHistoryMonth()
}