package com.handroid.currencyconverter.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.handroid.currencyconverter.data.database.model.CoinInfoModel

@Database(entities = [CoinInfoModel::class], version = 1, exportSchema = false)
abstract class AppCoinDatabase(
) : RoomDatabase() {
    companion object {
        private var db: AppCoinDatabase? = null
        private const val DB_NAME = "coin.db"
        private val LOCK = Any()
        fun getInstance(
            context: Context
        ): AppCoinDatabase {
            synchronized(LOCK) {
                db?.let { return it }
                val instance = Room.databaseBuilder(
                    context,
                    AppCoinDatabase::class.java,
                    DB_NAME
                )
                    .fallbackToDestructiveMigration()
                    .build()
                db = instance
                return instance
            }
        }
    }
    abstract fun coinInfoDao(): CoinInfoDao
}