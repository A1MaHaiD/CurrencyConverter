package com.handroid.currencyconverter.data.network.dto.history

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class HistoryInfoListContainerDto(
    @SerializedName("Data")
    @Expose
    val history: HistoryInfoListDto? = null
)