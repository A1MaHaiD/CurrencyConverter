package com.handroid.currencyconverter.domain.entity

import com.handroid.currencyconverter.data.database.model.HistoryInfoModel
import javax.inject.Inject

data class HistoryMapContainerEntity @Inject constructor (
    val fromSymbols: String,
    val historyInfoModel: HistoryInfoModel
)