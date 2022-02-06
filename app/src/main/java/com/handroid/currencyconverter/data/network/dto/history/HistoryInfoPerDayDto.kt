package com.handroid.currencyconverter.data.network.dto.history

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

@Entity(tableName = "history_per_day")
data class HistoryInfoPerDayDto(
    @SerializedName("time")
    @Expose
    @PrimaryKey
    val time: Int? = null,
    @SerializedName("high")
    @Expose
    val high: Double? = null,
    @SerializedName("low")
    @Expose
    val low: Double? = null,
    @SerializedName("open")
    @Expose
    val open: Double? = null,
    @SerializedName("volumefrom")
    @Expose
    val volumeFrom: Double? = null,
    @SerializedName("volumeto")
    @Expose
    val volumeTo: Double? = null,
    @SerializedName("close")
    @Expose
    val close: Double? = null,
    @SerializedName("conversionType")
    @Expose
    val conversionType: String? = null,
    @SerializedName("conversionSymbol")
    @Expose
    val conversionSymbol: String? = null
)