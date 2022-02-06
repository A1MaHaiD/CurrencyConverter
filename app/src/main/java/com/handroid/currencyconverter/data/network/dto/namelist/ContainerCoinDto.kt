package com.handroid.currencyconverter.data.network.dto.namelist

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class ContainerCoinDto(
    @SerializedName("CoinInfo")
    @Expose
    val coinName: CoinNameDto? = null
)