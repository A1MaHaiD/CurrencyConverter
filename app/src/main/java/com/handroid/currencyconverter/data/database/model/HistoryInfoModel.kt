package com.handroid.currencyconverter.data.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "history_per_day")
data class HistoryInfoModel(
    @PrimaryKey
    val time: Int?,
    val high: Double?,
    val low: Double?,
    val open: Double?,
    val volumeFrom: Double?,
    val volumeTo: Double?,
    val close: Double?,
    val conversionType: String?,
    val conversionSymbol: String?
)