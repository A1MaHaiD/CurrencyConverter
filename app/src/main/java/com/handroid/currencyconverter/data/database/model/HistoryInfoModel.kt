package com.handroid.currencyconverter.data.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import javax.inject.Inject

@Entity(tableName = "history_per_day")
data class HistoryInfoModel @Inject constructor(
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