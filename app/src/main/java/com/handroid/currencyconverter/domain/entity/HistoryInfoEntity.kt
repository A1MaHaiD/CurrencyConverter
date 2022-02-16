package com.handroid.currencyconverter.domain.entity

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import javax.inject.Inject

@Parcelize
data class HistoryInfoEntity @Inject constructor(
    val time: String,
    val high: String?,
    val low: String?,
    val open: String?,
    val close: String?,
) : Parcelable
