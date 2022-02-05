package com.handroid.currencyconverter.data.network.dto

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class ListOfContainerDto(
    @SerializedName("Data")
    @Expose
    val data: List<ContainerCoinDto>? = null
)