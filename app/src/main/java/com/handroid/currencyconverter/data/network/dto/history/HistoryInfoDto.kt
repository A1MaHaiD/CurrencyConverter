package com.handroid.currencyconverter.data.network.dto.history

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class HistoryInfoDto(
    @SerializedName("time")
    @Expose
    val time: Int,
    @SerializedName("high")
    @Expose
    val high: Double?,
    @SerializedName("low")
    @Expose
    val low: Double?,
    @SerializedName("open")
    @Expose
    val open: Double?,
    @SerializedName("close")
    @Expose
    val close: Double?
)