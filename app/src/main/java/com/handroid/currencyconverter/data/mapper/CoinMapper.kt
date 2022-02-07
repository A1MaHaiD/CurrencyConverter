package com.handroid.currencyconverter.data.mapper

import com.google.gson.Gson
import com.handroid.currencyconverter.data.database.model.CoinInfoModel
import com.handroid.currencyconverter.data.network.dto.detailinfo.CoinInfoDto
import com.handroid.currencyconverter.data.network.dto.detailinfo.CoinInfoJsonContainerDto
import com.handroid.currencyconverter.data.network.dto.namelist.CoinNameListDto
import com.handroid.currencyconverter.domain.entity.CoinInfoEntity
import java.sql.Timestamp
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

class CoinMapper @Inject constructor() {

    fun mapDtoToModel(coinInfoDto: CoinInfoDto) = CoinInfoModel(
        fromSymbol = coinInfoDto.fromSymbol,
        toSymbol = coinInfoDto.toSymbol,
        price = coinInfoDto.price,
        lastUpdate = coinInfoDto.lastUpdate,
        highDay = coinInfoDto.highDay,
        lowDay = coinInfoDto.lowDay,
        lastMarket = coinInfoDto.lastMarket,
        imageUrl = coinInfoDto.imageUrl
    )

    fun mapModelToEntity(coinInfoModel: CoinInfoModel) = CoinInfoEntity(
        fromSymbol = coinInfoModel.fromSymbol,
        toSymbol = coinInfoModel.toSymbol,
        price = coinInfoModel.price,
        lastUpdate = coinInfoModel.lastUpdate,
        highDay = coinInfoModel.highDay,
        lowDay = coinInfoModel.lowDay,
        lastMarket = coinInfoModel.lastMarket,
        imageUrl = coinInfoModel.imageUrl
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

    fun mapNameListToString(nameListDto: CoinNameListDto): String {
        return nameListDto.names?.map {
            it.coinName?.name
        }?.joinToString(",") ?: ""
    }

    fun convertTimestampToTime(timestamp: Long?): String {
        timestamp?.let {
            val sdf = SimpleDateFormat("HH:mm:ss", Locale.getDefault())
            sdf.timeZone = TimeZone.getDefault()
            return sdf.format(Date(Timestamp(timestamp * 1000).time))
        }
        return ""
    }
}