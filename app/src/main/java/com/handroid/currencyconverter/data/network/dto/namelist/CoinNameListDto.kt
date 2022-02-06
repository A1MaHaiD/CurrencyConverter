package com.handroid.currencyconverter.data.network.dto.namelist

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class  CoinNameListDto(
    @SerializedName("Data")
    @Expose
    val name: List<ContainerCoinDto>? = null
)