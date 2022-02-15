package com.handroid.currencyconverter.data.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.handroid.currencyconverter.data.database.model.HistoryInfoModel
import com.handroid.currencyconverter.data.database.model.HistoryMapContainerModel
import com.handroid.currencyconverter.domain.entity.HistoryInfoEntity
import com.handroid.currencyconverter.domain.entity.HistoryMapContainerEntity

@Dao
interface HistoryInfoDao {

    @Query("SELECT * FROM history_per_day ORDER BY fromSymbols DESC")
    fun getHistoryList(): LiveData<List<HistoryMapContainerModel>>

    @Query("SELECT * FROM history_per_day WHERE fromSymbols ==:fromSymbols")
    fun getHistoryPerMonth(fromSymbols: String): LiveData<List<HistoryInfoModel>>

    @Query("SELECT * FROM history_per_day WHERE fromSymbols ==:fromSymbols & historyInfoModel ==:historyInfoModel")
    fun getHistoryPerDay(
        fromSymbols: String,
        historyInfoModel: HistoryInfoModel
    ): LiveData<HistoryInfoModel>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertHistoryList(historyList: List<HistoryMapContainerModel>)
}