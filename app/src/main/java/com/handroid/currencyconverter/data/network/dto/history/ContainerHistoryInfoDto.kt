package com.handroid.currencyconverter.data.network.dto.history

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName

class ContainerHistoryInfoDto {
    @SerializedName("Data")
    @Expose
    private val data: List<HistoryInfoPerDayDto>? = null
}