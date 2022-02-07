package com.handroid.currencyconverter.data.network.dto.namelist

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import javax.inject.Inject

data class  CoinNameListDto @Inject constructor(
    @SerializedName("Data")
    @Expose
    val names: List<CoinNameContainerDto>? = null
)