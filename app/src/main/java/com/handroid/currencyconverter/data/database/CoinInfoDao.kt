package com.handroid.currencyconverter.data.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.handroid.currencyconverter.data.database.model.CoinInfoModel

@Dao
interface CoinInfoDao {

    @Query("SELECT * FROM currently_full_data ORDER BY lastUpdate DESC")
    fun getPriceList(): LiveData<List<CoinInfoModel>>

    @Query("SELECT * FROM currently_full_data WHERE fromSymbol ==:fSym LIMIT 1")
    fun getFullCoinInfo(fSym: String): LiveData<CoinInfoModel>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPriceList(priceList: List<CoinInfoModel>)
}