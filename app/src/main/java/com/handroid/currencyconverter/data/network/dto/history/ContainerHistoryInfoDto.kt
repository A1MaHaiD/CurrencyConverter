package com.handroid.currencyconverter.data.network.dto.history

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName

data class ContainerHistoryInfoDto(
    @SerializedName("Data")
    @Expose
    val history: HistoryListDto? = null
)