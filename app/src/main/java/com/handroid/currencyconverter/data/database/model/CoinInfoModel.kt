package com.handroid.currencyconverter.data.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "currently_full_data")
data class CoinInfoModel(
    @PrimaryKey
    val fromSymbol: String,
    val toSymbol: String?,
    val price: Double?,
    val lastUpdate: Float?,
    val highDay: Double?,
    val lowDay: Double?,
    val lastMarket: String?,
    val imageUrl: String?
)
