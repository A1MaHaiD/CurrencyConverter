package com.handroid.currencyconverter.domain.entity

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import javax.inject.Inject

@Parcelize
data class CoinInfoEntity @Inject constructor(
    val fromSymbol: String,
    val toSymbol: String?,
    val price: String?,
    val lastUpdate: String,
    val highDay: String?,
    val lowDay: String?,
    val lastMarket: String?,
    val imageUrl: String
) : Parcelable
