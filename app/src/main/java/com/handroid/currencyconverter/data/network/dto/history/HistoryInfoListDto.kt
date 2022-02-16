package com.handroid.currencyconverter.data.network.dto.history

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class HistoryInfoListDto(
    @SerializedName("Data")
    @Expose
    val historyList: List<HistoryInfoDto>
)