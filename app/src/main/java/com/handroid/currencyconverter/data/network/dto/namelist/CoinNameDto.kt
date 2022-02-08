package com.handroid.currencyconverter.data.network.dto.namelist

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import javax.inject.Inject

data class CoinNameDto @Inject constructor(
    @SerializedName("Name")
    @Expose
    val name: String? = null,
)
