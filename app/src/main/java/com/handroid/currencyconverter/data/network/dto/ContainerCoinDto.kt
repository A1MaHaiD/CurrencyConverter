package com.handroid.currencyconverter.data.network.dto

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class ContainerCoinDto(
    @SerializedName("CoinInfo")
    @Expose
    val coinInfo: CoinDto? = null
)