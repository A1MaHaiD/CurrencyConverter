package com.handroid.currencyconverter.data.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import javax.inject.Inject

@Entity(tableName = "history_per_day")
data class HistoryMapContainerModel @Inject constructor(
    @PrimaryKey
    val fromSymbols: String,
    val historyInfoModel: List<HistoryInfoModel>
)