package com.handroid.currencyconverter.data.network.dto.history

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class HistoryInfoPerDayDto(
    @SerializedName("time")
    @Expose
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
    val volumefrom: Double? = null,
    @SerializedName("volumeto")
    @Expose
    val volumeto: Double? = null,
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