package com.handroid.currencyconverter.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.handroid.currencyconverter.data.database.model.CoinInfoModel
import com.handroid.currencyconverter.data.database.model.HistoryInfoModel

@Database(entities = [HistoryInfoModel::class], version = 1, exportSchema = false)
abstract class AppHistoryDatabase(
) : RoomDatabase() {
    companion object {
        private var db: AppHistoryDatabase? = null
        private const val DB_NAME = "history.db"
        private val LOCK = Any()
        fun getInstance(
            context: Context
        ): AppHistoryDatabase {
            synchronized(LOCK) {
                db?.let { return it }
                val instance = Room.databaseBuilder(
                    context,
                    AppHistoryDatabase::class.java,
                    DB_NAME
                )
                    .fallbackToDestructiveMigration()
                    .build()
                db = instance
                return instance
            }
        }
    }
    abstract fun historyInfoDao(): HistoryInfoDao
}