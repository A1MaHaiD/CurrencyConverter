package com.handroid.currencyconverter.domain.usecase

import com.handroid.currencyconverter.domain.CoinRepository
import javax.inject.Inject

class LoadHistoryWeekUseCase @Inject constructor(
    private val repository: CoinRepository
) {
    suspend operator fun invoke() = repository.loadHistoryWeek()
}