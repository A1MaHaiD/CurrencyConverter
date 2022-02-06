package com.handroid.currencyconverter.data.network.dto.history

import com.google.gson.JsonObject
import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName

data class JsonHistoryObjectDto(
    @SerializedName("Data")
    @Expose
    val jsonHistoryDay: JsonObject? = null
)