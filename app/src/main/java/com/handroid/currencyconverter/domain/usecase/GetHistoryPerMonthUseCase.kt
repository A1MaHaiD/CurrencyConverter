package com.handroid.currencyconverter.domain.usecase

import com.handroid.currencyconverter.domain.CoinRepository
import javax.inject.Inject

class GetHistoryPerMonthUseCase @Inject constructor(
    private val repository: CoinRepository
) {
    operator fun invoke() = repository.getHistoryPerMonth()
}