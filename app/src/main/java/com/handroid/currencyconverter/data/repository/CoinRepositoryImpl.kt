package com.handroid.currencyconverter.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.handroid.currencyconverter.data.database.AppDatabase
import com.handroid.currencyconverter.data.mapper.CoinMapper
import com.handroid.currencyconverter.data.network.ApiFactory
import com.handroid.currencyconverter.domain.CoinRepository
import com.handroid.currencyconverter.domain.entity.CoinInfoEntity
import com.handroid.currencyconverter.domain.entity.HistoryInfoEntity
import kotlinx.coroutines.delay
import java.lang.Exception
import javax.inject.Inject

class CoinRepositoryImpl @Inject constructor(
    private val database: AppDatabase,
    private val api: ApiFactory,
    private val mapper: CoinMapper
) : CoinRepository {

    override fun getCoinList(): LiveData<List<CoinInfoEntity>> {
        return Transformations.map(database.coinInfoDao().getPriceList()) {
            it.map {
                mapper.mapModelToEntity(it)
            }
        }
    }

    override fun getCoinItem(fromSymbol: String): LiveData<CoinInfoEntity> {
        return Transformations.map(database.coinInfoDao().getFullCoinInfo(fromSymbol)) {
            mapper.mapModelToEntity(it)
        }
    }

    override fun getHistoryPerMonth(limit: Int): LiveData<HistoryInfoEntity> {
        TODO("Not yet implemented")
    }

    override fun getHistoryPerWeek(limit: Int): LiveData<HistoryInfoEntity> {
        TODO("Not yet implemented")
    }

    override suspend fun loadCoinDate() {
        while (true){
            try {
                val topCoins = api.apiService.getTopCoinInfo(limit = 30)
                val fSyms = mapper.mapNameListToString(topCoins)
                val jsonContainer = api.apiService.getFullPriceList(fSyms = fSyms)
                val coinInfoDtoList = mapper.mapJsonToListCoinInfo(jsonContainer)
                val dbModelList = coinInfoDtoList.map { mapper.mapDtoToModel(it) }
                database.coinInfoDao().insertPriceList(dbModelList)
            } catch (e: Exception) {
            }
            delay(30_000)
        }
    }

    override suspend fun loadHistoryData() {
        TODO("Not yet implemented")
    }
}