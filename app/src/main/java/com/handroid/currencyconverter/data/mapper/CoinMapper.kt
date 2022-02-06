package com.handroid.currencyconverter.data.mapper

import java.sql.Timestamp
import java.text.SimpleDateFormat
import java.util.*

class CoinMapper {

    fun convertTimestampToTime(timestamp: Long?): String {
        timestamp?.let {
            val sdf = SimpleDateFormat("HH:mm:ss", Locale.getDefault())
            sdf.timeZone = TimeZone.getDefault()
            return sdf.format(Date(Timestamp(timestamp * 1000).time))
        }
        return ""
    }
}