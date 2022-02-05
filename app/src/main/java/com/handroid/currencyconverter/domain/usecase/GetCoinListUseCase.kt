package com.handroid.currencyconverter.domain.usecase

import com.handroid.currencyconverter.domain.CoinRepository

class GetCoinListUseCase(
    private val repository: CoinRepository
) {
    operator fun invoke() = repository.getCoinList()
}