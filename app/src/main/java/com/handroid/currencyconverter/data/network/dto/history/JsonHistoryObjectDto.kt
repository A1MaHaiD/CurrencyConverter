package com.handroid.currencyconverter.data.network.dto.history

import com.google.gson.JsonObject
import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName
import javax.inject.Inject

data class JsonHistoryObjectDto @Inject constructor(
    @SerializedName("Data")
    @Expose
    val jsonHistoryDay: JsonObject? = null
)