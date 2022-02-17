package com.handroid.currencyconverter.data.network

import com.handroid.currencyconverter.data.network.dto.detailinfo.CoinInfoJsonContainerDto
import com.handroid.currencyconverter.data.network.dto.history.HistoryInfoListContainerDto
import com.handroid.currencyconverter.data.network.dto.history.JsonHistoryObjectDto
import com.handroid.currencyconverter.data.network.dto.namelist.CoinNameListDto
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("top/totalvolfull")
    suspend fun getTopCoinInfo(
        @Query(QUERY_PARAM_API_KEY) apiKey: String = "",
        @Query(QUERY_PARAM_LIMIT) limit: Int,
        @Query(QUERY_PARAM_TO_SYMBOL) tSym: String = CURRENCY
    ): CoinNameListDto

    @GET("pricemultifull")
    suspend fun getFullPriceList(
        @Query(QUERY_PARAM_API_KEY) apiKey: String = "",
        @Query(QUERY_PARAM_FROM_SYMBOLS) fSyms: String?,
        @Query(QUERY_PARAM_TO_SYMBOLS) tSyms: String = CURRENCY
    ): CoinInfoJsonContainerDto

    @GET("v2/histoday")
    suspend fun getCoinInfoPerDay(
        @Query(QUERY_PARAM_API_KEY) apiKey: String = "",
        @Query(QUERY_PARAM_FROM_SYMBOL) fSym: String,
        @Query(QUERY_PARAM_TO_SYMBOL) tSym: String = CURRENCY,
        @Query(QUERY_PARAM_LIMIT) limit: Int,
        @Query(QUERY_PARAM_AGGREGATE) aggregate: Int = QUERY_VAlUE_AGGREGATE,
        @Query(QUERY_PARAM_E) e: String = QUERY_VALUE_E
//    ): HistoryInfoListContainerDto
    ): JsonHistoryObjectDto

    companion object {
        const val QUERY_PARAM_API_KEY = "api_key"
        const val QUERY_PARAM_LIMIT = "limit"

        const val QUERY_PARAM_TO_SYMBOL = "tsym"
        const val QUERY_PARAM_FROM_SYMBOL = "fsym"
        const val QUERY_PARAM_AGGREGATE = "aggregate"
        const val QUERY_PARAM_E = "e"
        const val QUERY_VALUE_E = "CCCAGG"
        const val QUERY_VAlUE_AGGREGATE = 1

        const val QUERY_PARAM_TO_SYMBOLS = "tsyms"
        const val QUERY_PARAM_FROM_SYMBOLS = "fsyms"
        const val CURRENCY = "USD"
    }
}