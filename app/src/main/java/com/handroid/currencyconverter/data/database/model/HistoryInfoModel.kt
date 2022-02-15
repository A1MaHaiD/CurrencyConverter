package com.handroid.currencyconverter.data.database.model

import javax.inject.Inject

data class HistoryInfoModel @Inject constructor(
    val time: Int?,
    val high: Double?,
    val low: Double?,
    val open: Double?,
    val close: Double?,
)