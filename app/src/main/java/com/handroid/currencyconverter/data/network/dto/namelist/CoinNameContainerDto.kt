package com.handroid.currencyconverter.data.network.dto.namelist

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import javax.inject.Inject

data class CoinNameContainerDto @Inject constructor(
    @SerializedName("CoinInfo")
    @Expose
    val coinName: CoinNameDto? = null
)