package com.handroid.currencyconverter.domain.entity

data class HistoryInfoEntity (
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
