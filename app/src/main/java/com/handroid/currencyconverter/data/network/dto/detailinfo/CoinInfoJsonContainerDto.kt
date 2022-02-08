package com.handroid.currencyconverter.data.network.dto.detailinfo

import com.google.gson.JsonObject
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import javax.inject.Inject

data class CoinInfoJsonContainerDto @Inject constructor(
    @SerializedName("RAW")
    @Expose
    val jsonCoinObject: JsonObject? = null
)