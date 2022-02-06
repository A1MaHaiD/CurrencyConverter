package com.handroid.currencyconverter.data.network.dto.list

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.handroid.currencyconverter.data.network.dto.item.ContainerCoinDto

data class ListOfContainerDto(
    @SerializedName("Data")
    @Expose
    val data: List<ContainerCoinDto>? = null
)