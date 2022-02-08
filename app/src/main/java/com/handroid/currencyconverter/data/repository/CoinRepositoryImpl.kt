package com.handroid.currencyconverter.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.handroid.currencyconverter.data.database.AppDatabase
import com.handroid.currencyconverter.data.mapper.CoinMapper
import com.handroid.currencyconverter.data.network.ApiFactory
import com.handroid.currencyconverter.data.network.dto.history.HistoryInfoDto
import com.handroid.currencyconverter.data.network.dto.namelist.CoinNameListDto
import com.handroid.currencyconverter.domain.CoinRepository
import com.handroid.currencyconverter.domain.entity.CoinInfoEntity
import com.handroid.currencyconverter.domain.entity.HistoryInfoEntity
import kotlinx.coroutines.delay
import javax.inject.Inject

class CoinRepositoryImpl @Inject constructor(
    private val database: AppDatabase,
    private val api: ApiFactory,
    private val mapper: CoinMapper
) : CoinRepository {

    override fun getCoinList(): LiveData<List<CoinInfoEntity>> {
        return Transformations.map(database.coinInfoDao().getPriceList()) {
            it.map {
                mapper.mapCoinModelToEntity(it)
            }
        }
    }

    override fun getCoinItem(fromSymbol: String): LiveData<CoinInfoEntity> {
        return Transformations.map(database.coinInfoDao().getFullCoinInfo(fromSymbol)) {
            mapper.mapCoinModelToEntity(it)
        }
    }

    override fun getHistoryPerDay(time: Int): LiveData<HistoryInfoEntity> {
        return Transformations.map(database.historyInfoDao().getHistoryPerDay(time)) {
            mapper.mapHistoryModelToEntity(it)
        }
    }

    override fun getHistoryPerMonth(): LiveData<List<HistoryInfoEntity>> {
        return Transformations.map(database.historyInfoDao().getHistoryPerPeriod()) {
            it.map {
                mapper.mapHistoryModelToEntity(it)
            }
        }
    }

    override fun getHistoryPerWeek(): LiveData<List<HistoryInfoEntity>> {
        return Transformations.map(database.historyInfoDao().getHistoryPerPeriod()) {
            it.subList(0, 7).map {
                mapper.mapHistoryModelToEntity(it)
            }
        }
    }

    override suspend fun loadCoinDate() {
        while (true) {
            try {
                val topCoins = api.apiService.getTopCoinInfo(limit = 30)
                val fSyms = mapper.mapNameListToString(topCoins)
                val jsonContainer = api.apiService.getFullPriceList(fSyms = fSyms)
                val coinInfoDtoList = mapper.mapJsonToListCoinInfo(jsonContainer)
                val dbModelList = coinInfoDtoList.map { mapper.mapCoinDtoToModel(it) }
                database.coinInfoDao().insertPriceList(dbModelList)
            } catch (e: Exception) {
            }
            delay(30_000)
        }
    }

    override suspend fun loadHistoryMonth() {
        while (true) {
            try {
                val topCoins = api.apiService.getTopCoinInfo(limit = 30)
                val fSyms = CoinNameListDto().names.toString()
                var historyInfoDtoList: List<HistoryInfoDto>? = null
                for (request in fSyms) {
                    val historyByMonth = api.apiService.getCoinInfoPerDay(fSym = fSyms, limit = 30)
                    historyInfoDtoList = mapper.mapJsonToListHistoryInfo(historyByMonth)
                }
                historyInfoDtoList?.let {
                    val dbModelList =
                        historyInfoDtoList.map { mapper.mapHistoryDtoToModel(it) }
                    database.historyInfoDao().insertHistoryList(dbModelList)
                }
            } catch (e: Exception) {
            }
            delay(2_160_000)
        }
    }
}