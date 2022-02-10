package com.handroid.currencyconverter.data.mapper

import com.google.gson.Gson
import com.handroid.currencyconverter.data.database.model.CoinInfoModel
import com.handroid.currencyconverter.data.database.model.HistoryInfoModel
import com.handroid.currencyconverter.data.network.dto.detailinfo.CoinInfoDto
import com.handroid.currencyconverter.data.network.dto.detailinfo.CoinInfoJsonContainerDto
import com.handroid.currencyconverter.data.network.dto.history.HistoryInfoDto
import com.handroid.currencyconverter.data.network.dto.history.JsonHistoryObjectDto
import com.handroid.currencyconverter.data.network.dto.namelist.CoinNameListDto
import com.handroid.currencyconverter.domain.entity.CoinInfoEntity
import com.handroid.currencyconverter.domain.entity.HistoryInfoEntity
import java.sql.Timestamp
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

class CoinMapper @Inject constructor() {

    fun mapCoinDtoToModel(coinInfoDto: CoinInfoDto) = CoinInfoModel(
        fromSymbol = coinInfoDto.fromSymbol,
        toSymbol = coinInfoDto.toSymbol,
        price = coinInfoDto.price,
        lastUpdate = coinInfoDto.lastUpdate,
        highDay = coinInfoDto.highDay,
        lowDay = coinInfoDto.lowDay,
        lastMarket = coinInfoDto.lastMarket,
        imageUrl = BASE_IMAGE_URL + coinInfoDto.imageUrl
    )

    fun mapCoinModelToEntity(coinInfoModel: CoinInfoModel) = CoinInfoEntity(
        fromSymbol = coinInfoModel.fromSymbol,
        toSymbol = coinInfoModel.toSymbol,
        price = coinInfoModel.price.toString(),
        lastUpdate = convertTimestampToTime(coinInfoModel.lastUpdate),
        highDay = coinInfoModel.highDay.toString(),
        lowDay = coinInfoModel.lowDay.toString(),
        lastMarket = coinInfoModel.lastMarket,
        imageUrl = coinInfoModel.imageUrl
    )

    fun mapHistoryModelToEntity(historyInfoModel: HistoryInfoModel) = HistoryInfoEntity(
        time = convertTimestampToTime(historyInfoModel.time?.toLong()),
        high = historyInfoModel.high.toString(),
        low = historyInfoModel.low.toString(),
        open = historyInfoModel.open.toString(),
        volumeFrom = historyInfoModel.volumeFrom.toString(),
        volumeTo = historyInfoModel.volumeTo.toString(),
        close = historyInfoModel.close.toString(),
        conversionType = historyInfoModel.conversionType,
        conversionSymbol = historyInfoModel.conversionSymbol
    )

    fun mapHistoryDtoToModel(historyInfoDto: HistoryInfoDto) = HistoryInfoModel(
        time = historyInfoDto.time,
        high = historyInfoDto.high,
        low = historyInfoDto.low,
        open = historyInfoDto.open,
        volumeFrom = historyInfoDto.volumeFrom,
        volumeTo = historyInfoDto.volumeTo,
        close = historyInfoDto.close,
        conversionType = historyInfoDto.conversionType,
        conversionSymbol = historyInfoDto.conversionSymbol
    )

    fun mapJsonToListCoinInfo(jsonDto: CoinInfoJsonContainerDto): List<CoinInfoDto> {
        val result = mutableListOf<CoinInfoDto>()
        val jsonObject = jsonDto.jsonCoinObject ?: return result
        val coinKeySet = jsonObject.keySet()
        for (coinKey in coinKeySet) {
            val currencyJson = jsonObject.getAsJsonObject(coinKey)
            val currencyKeySet = currencyJson.keySet()
            for (currencyKey in currencyKeySet) {
                val priceInfo = Gson().fromJson(
                    currencyJson.getAsJsonObject(currencyKey),
                    CoinInfoDto::class.java
                )
                result.add(priceInfo)
            }
        }
        return result
    }

    fun mapJsonToListHistoryInfo(historyObject: JsonHistoryObjectDto):List<HistoryInfoDto>{
        val result = mutableListOf<HistoryInfoDto>()
        val jsonObject = historyObject.jsonHistoryDay ?: return result
        val historyKeySet = jsonObject.keySet()
        for (historyKey in historyKeySet){
            val currencyJson = jsonObject.getAsJsonObject(historyKey)
            val currencyKeySet = currencyJson.keySet()
            for (currencyKey in currencyKeySet){
                val  historyInfo = Gson().fromJson(
                    currencyJson.getAsJsonObject(currencyKey),
                    HistoryInfoDto::class.java
                )
                result.add(historyInfo)
            }
        }
        return result
    }

    fun mapNameListToString(nameListDto: CoinNameListDto): String {
        return nameListDto.names?.map {
            it.coinName?.name
        }?.joinToString(",") ?: ""
    }

    private fun convertTimestampToTime(timestamp: Long?): String {
        timestamp?.let {
            val sdf = SimpleDateFormat("HH:mm:ss", Locale.getDefault())
            sdf.timeZone = TimeZone.getDefault()
            return sdf.format(Date(Timestamp(timestamp * 1000).time))
        }
        return ""
    }

    companion object {
        private const val BASE_IMAGE_URL = "https://min-api.cryptocompare.com"
    }
}