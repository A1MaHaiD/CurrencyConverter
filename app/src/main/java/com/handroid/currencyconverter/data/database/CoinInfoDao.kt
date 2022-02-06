package com.handroid.currencyconverter.data.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.handroid.currencyconverter.data.network.dto.detailinfo.CoinInfoDto
import com.handroid.currencyconverter.data.network.dto.history.HistoryInfoPerDayDto

@Dao
interface CoinInfoDao {

    @Query("SELECT * FROM currently_full_data ORDER BY lastUpdate")
    fun getPriceList(): LiveData<List<CoinInfoDto>>

    @Query("SELECT * FROM currently_full_data WHERE fromSymbol ==:fSym LIMIT 1")
    fun getFullCoinInfo(fSym: String): LiveData<CoinInfoDto>

    @Query("SELECT * FROM history_per_day ORDER BY time")
    fun getHistoryList(): LiveData<List<HistoryInfoPerDayDto>>

    @Query("SELECT * FROM history_per_day WHERE time ==:time LIMIT 1")
    fun getHistoryDay(time: Int): LiveData<HistoryInfoPerDayDto>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertPriceList(priceList: List<CoinInfoDto>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertHistoryList(historyList: List<HistoryInfoPerDayDto>)
}