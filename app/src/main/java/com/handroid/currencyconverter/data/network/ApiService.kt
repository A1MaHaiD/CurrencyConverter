package com.handroid.currencyconverter.data.network

import com.handroid.currencyconverter.data.network.dto.history.JsonHistoryContainerDto
import com.handroid.currencyconverter.data.network.dto.item.CoinJsonObjectDTO
import com.handroid.currencyconverter.data.network.dto.item.ContainerCoinDto
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("top/totalvolfull")
    suspend fun getTopCoinInfo(
        @Query(QUERY_PARAM_API_KEY) api: String = "",
        @Query(QUERY_PARAM_LIMIT) limit: Int = 10,
        @Query(QUERY_PARAM_TO_SYMBOL) tSym: String = CURRENCY
    ): ContainerCoinDto

    @GET("pricemultifull")
    suspend fun getFullPriceList(
        @Query(QUERY_PARAM_API_KEY) api: String = "",
        @Query(QUERY_PARAM_FROM_SYMBOL) fSyms: String,
        @Query(QUERY_PARAM_TO_SYMBOLS) tSyms: String = CURRENCY
    ): List<CoinJsonObjectDTO>

    @GET("v2/histoday")
    suspend fun getCoinInfoPerMonth(
        @Query(QUERY_PARAM_API_KEY) api: String = "",
        @Query(QUERY_PARAM_FROM_SYMBOL) fSym: String,
        @Query(QUERY_PARAM_TO_SYMBOL) tSym: String = CURRENCY,
        @Query(QUERY_PARAM_LIMIT) limit: Int = 30,
        @Query(QUERY_PARAM_AGGREGATE) aggregate: Int = 1,
        @Query(QUERY_PARAM_E) e: String = QUERY_VALUE_E
    ): List<JsonHistoryContainerDto>

    @GET("v2/histoday")
    suspend fun getCoinInfoPerWeek(
        @Query(QUERY_PARAM_API_KEY) api: String = "",
        @Query(QUERY_PARAM_FROM_SYMBOL) fSym: String,
        @Query(QUERY_PARAM_TO_SYMBOL) tSym: String = CURRENCY,
        @Query(QUERY_PARAM_LIMIT) limit: Int = 6,
        @Query(QUERY_PARAM_AGGREGATE) aggregate: Int = 1,
        @Query(QUERY_PARAM_E) e: String = QUERY_VALUE_E
    ):List<JsonHistoryContainerDto>

    companion object {
        const val QUERY_PARAM_API_KEY = "api"
        const val QUERY_PARAM_LIMIT = "limit"

        const val QUERY_PARAM_TO_SYMBOL = "tsym"
        const val QUERY_PARAM_FROM_SYMBOL = "fsym"
        const val QUERY_PARAM_AGGREGATE = "aggregate"
        const val QUERY_PARAM_E = "e"
        const val QUERY_VALUE_E = "CCCAGG"

        const val QUERY_PARAM_TO_SYMBOLS = "tsyms"
        const val QUERY_PARAM_FROM_SYMBOLS = "fsyms"
        const val CURRENCY = "USD"
    }
}