package com.handroid.currencyconverter.domain.usecase

import com.handroid.currencyconverter.domain.CoinRepository
import javax.inject.Inject

class GetHistoryPerDayUseCase @Inject constructor(
    private val repository: CoinRepository
) {
    operator fun invoke(time: Int) = repository.getHistoryPerDay(time)
}