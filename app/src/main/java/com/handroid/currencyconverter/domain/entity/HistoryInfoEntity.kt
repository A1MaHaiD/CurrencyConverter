package com.handroid.currencyconverter.domain.entity

data class HistoryInfoEntity(
    val time: Int?,
    val high: Double?,
    val low: Double?,
    val open: Double?,
    val volumeFrom: Double?,
    val volumeTo: Double?,
    val close: Double?,
    val conversionType: String?,
    val conversionSymbol: String?
)
