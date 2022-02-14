package com.handroid.currencyconverter.domain.entity

import javax.inject.Inject

data class HistoryInfoEntity @Inject constructor(
    val time: String,
    val high: String?,
    val low: String?,
    val open: String?,
    val volumeFrom: String?,
    val volumeTo: String?,
    val close: String?,
    val conversionType: String?,
    val conversionSymbol: String?
)
