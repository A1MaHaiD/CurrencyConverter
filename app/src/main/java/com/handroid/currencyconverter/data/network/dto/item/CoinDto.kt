package com.handroid.currencyconverter.data.network.dto.item

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class CoinDto(
    @SerializedName("Id")
    @Expose
    val id: String? = null,
    @SerializedName("Name")
    @Expose
    val name: String? = null,
    @SerializedName("FullName")
    @Expose
    val fullName: String? = null,
    @SerializedName("ImageUrl")
    @Expose
    val imageUrl: String? = null
)
