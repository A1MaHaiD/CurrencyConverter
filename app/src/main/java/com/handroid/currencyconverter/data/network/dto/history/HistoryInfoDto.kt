package com.handroid.currencyconverter.data.network.dto.history

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName

class HistoryInfoDto {
    @SerializedName("Data")
    @Expose
    val data: ContainerHistoryInfoDto? = null
}