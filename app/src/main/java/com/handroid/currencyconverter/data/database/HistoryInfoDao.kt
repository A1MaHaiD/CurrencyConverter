package com.handroid.currencyconverter.data.database

import androidx.lifecycle.LiveData
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.handroid.currencyconverter.data.database.model.HistoryInfoModel

interface HistoryInfoDao {

    @Query("SELECT * FROM history_per_day ORDER BY time DESC")
    fun getHistoryPerPeriod(): LiveData<List<HistoryInfoModel>>

    @Query("SELECT * FROM history_per_day WHERE time ==:time LIMIT 1")
    fun getHistoryPerDay(time: Int): LiveData<HistoryInfoModel>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertHistoryList(historyList: List<HistoryInfoModel>)
}