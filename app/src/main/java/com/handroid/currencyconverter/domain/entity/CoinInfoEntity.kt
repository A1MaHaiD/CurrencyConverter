package com.handroid.currencyconverter.domain.entity

import javax.inject.Inject

data class CoinInfoEntity @Inject constructor(
    val fromSymbol: String,
    val toSymbol: String?,
    val price: Double?,
    val lastUpdate: Float?,
    val highDay: Double?,
    val lowDay: Double?,
    val lastMarket: String?,
    val imageUrl: String?
)
