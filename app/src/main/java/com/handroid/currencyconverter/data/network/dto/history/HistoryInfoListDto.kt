package com.handroid.currencyconverter.data.network.dto.history

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class HistoryInfoListDto(
    @SerializedName("Aggregated")
    @Expose
    val aggregated: Boolean? = null,
    @SerializedName("TimeFrom")
    @Expose
    val timeFrom: Int? = null,
    @SerializedName("TimeTo")
    @Expose
    val timeTo: Int? = null,
    @SerializedName("Data")
    @Expose
    val historyList: List<HistoryInfoDto>? = null
)