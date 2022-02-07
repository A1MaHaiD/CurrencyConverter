package com.handroid.currencyconverter.data.network.dto.history

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import javax.inject.Inject

data class HistoryInfoDto @Inject constructor(
    @SerializedName("time")
    @Expose
    val time: Int?,
    @SerializedName("high")
    @Expose
    val high: Double?,
    @SerializedName("low")
    @Expose
    val low: Double?,
    @SerializedName("open")
    @Expose
    val open: Double?,
    @SerializedName("volumefrom")
    @Expose
    val volumeFrom: Double?,
    @SerializedName("volumeto")
    @Expose
    val volumeTo: Double?,
    @SerializedName("close")
    @Expose
    val close: Double?,
    @SerializedName("conversionType")
    @Expose
    val conversionType: String?,
    @SerializedName("conversionSymbol")
    @Expose
    val conversionSymbol: String?
)