package com.handroid.currencyconverter.data.network.dto.history

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import javax.inject.Inject

data class ContainerHistoryInfoListDto @Inject constructor(
    @SerializedName("Data")
    @Expose
    val history: HistoryInfoListDto? = null
)