package com.handroid.currencyconverter.data.mapper

import android.util.Log
import com.google.firebase.ktx.Firebase
import com.google.firebase.remoteconfig.ktx.remoteConfig
import com.google.firebase.remoteconfig.ktx.remoteConfigSettings
import com.google.gson.Gson
import com.handroid.currencyconverter.R
import com.handroid.currencyconverter.data.database.model.CoinInfoModel
import com.handroid.currencyconverter.data.database.model.HistoryInfoModel
import com.handroid.currencyconverter.data.network.dto.detailinfo.CoinInfoDto
import com.handroid.currencyconverter.data.network.dto.detailinfo.CoinInfoJsonContainerDto
import com.handroid.currencyconverter.data.network.dto.history.HistoryInfoDto
import com.handroid.currencyconverter.data.network.dto.history.HistoryInfoListContainerDto
import com.handroid.currencyconverter.data.network.dto.history.JsonHistoryObjectDto
import com.handroid.currencyconverter.data.network.dto.namelist.CoinNameListDto
import com.handroid.currencyconverter.data.workers.RefreshCoinDataWorker
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

    fun mapHistoryDtoToModel(historyInfoDto: HistoryInfoDto) = HistoryInfoModel(
        time = historyInfoDto.time,
        high = historyInfoDto.high,
        low = historyInfoDto.low,
        open = historyInfoDto.open,
        close = historyInfoDto.close,
    )

    fun mapHistoryModelToEntity(historyInfoModel: HistoryInfoModel) = HistoryInfoEntity(
        time = convertTimestampToDate(historyInfoModel.time.toLong()),
        high = historyInfoModel.high.toString(),
        low = historyInfoModel.low.toString(),
        open = historyInfoModel.open.toString(),
        close = historyInfoModel.close.toString(),
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

    fun mapJsonToListHistoryInfo(historyObject: JsonHistoryObjectDto): List<HistoryInfoDto> {
        val result = mutableListOf<HistoryInfoDto>()
        val jsonObject = historyObject.jsonHistoryDay ?: return result
        val historyKeySet = jsonObject.keySet()
        for (currencyKey in historyKeySet) {
            val currencyJsonDay = jsonObject.getAsJsonObject(currencyKey)
            val currencyKeySetDay = currencyJsonDay.keySet()
            for (dayKey in currencyKeySetDay) {
                val historyInfo = Gson().fromJson(
                    currencyJsonDay.getAsJsonObject(dayKey),
                    HistoryInfoDto::class.java
                )
                result.add(historyInfo)
            }
        }
        return result
    }

    fun mapContainerToListHistoryInfo(historyInfoListContainerDto: HistoryInfoListContainerDto): List<HistoryInfoDto> {
        val result = mutableListOf<HistoryInfoDto>()
        val historyInfoListDto = historyInfoListContainerDto.history
        val historyList = historyInfoListDto.historyList
        historyList.map {
            result.add(it)
        }
        return result
    }

    fun mapNameListToIterationName(nameListDto: CoinNameListDto): List<String> {
        val listOfNames = mutableListOf<String>()
        nameListDto.names?.map {
            listOfNames.add(it.copy().coinName?.name.toString()) //TODO firebase remote config
        }
        return listOfNames
    }

    fun mapNameListToString(nameListDto: CoinNameListDto): String {
        return nameListDto.names?.map {
            it.coinName?.name
        }?.joinToString(",") ?: "" //TODO firebase remote config
    }

    private fun convertTimestampToTime(timestamp: Long?): String {
        timestamp?.let {
            val sdf = SimpleDateFormat("HH:mm:ss", Locale.getDefault())
            sdf.timeZone = TimeZone.getDefault()
            return sdf.format(Date(Timestamp(timestamp * 1000).time))
        }
        return ""
    }

    private fun convertTimestampToDate(timestamp: Long?): String {
        timestamp?.let {
            val sdf = SimpleDateFormat("d MMM yyyy", Locale.getDefault())
            sdf.timeZone = TimeZone.getDefault()
            return sdf.format(Date(Timestamp(timestamp * 1000).time))
        }
        return ""
    }

    private val remoteConfig by lazy {
        Firebase.remoteConfig
    }

    private fun coinConfig() {
        val configSettings = remoteConfigSettings {
            minimumFetchIntervalInSeconds = 3600
        }
        with(remoteConfig) {
            setConfigSettingsAsync(configSettings)
            setDefaultsAsync(R.xml.show_default_crypto)
            getValueRemoteConfig()
        }
    }

    private fun getValueRemoteConfig() {
        remoteConfig.fetchAndActivate()
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val updated = task.result
                    Log.d(RefreshCoinDataWorker.NAME, "Config params updated: $updated")
                    //TODO()
                } else {
                    remoteConfig.getString(SHOW_DEFAULT_CRYPTO)
                    //TODO()
                }
            }
    }

    companion object {
        private const val BASE_IMAGE_URL = "https://cryptocompare.com"

        const val SHOW_DEFAULT_CRYPTO = "show_default_crypto"
        const val ON_SHOW_CONFIG = "on_show_config"
    }
}
