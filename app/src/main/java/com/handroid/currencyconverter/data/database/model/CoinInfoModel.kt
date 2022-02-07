package com.handroid.currencyconverter.data.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import javax.inject.Inject

@Entity(tableName = "currently_full_data")
data class CoinInfoModel @Inject constructor(
    @PrimaryKey
    val fromSymbol: String,
    val toSymbol: String?,
    val price: Double?,
    val lastUpdate: Long?,
    val highDay: Double?,
    val lowDay: Double?,
    val lastMarket: String?,
    val imageUrl: String
)
