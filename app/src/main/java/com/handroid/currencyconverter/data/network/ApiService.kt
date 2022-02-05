package com.handroid.currencyconverter.data.network

import com.handroid.currencyconverter.data.network.dto.ContainerCoinDto
import com.handroid.currencyconverter.data.network.dto.CoinJsonObjectDTO
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
        @Query(QUERY_PARAM_TO_SYMBOLS) tSyms:String = CURRENCY
    ): List<CoinJsonObjectDTO>


    companion object {
        const val QUERY_PARAM_API_KEY = "api"
        const val QUERY_PARAM_LIMIT = "limit"
        const val QUERY_PARAM_TO_SYMBOL = "tsym"
        const val CURRENCY = "USD"
        const val QUERY_PARAM_TO_SYMBOLS = "tsyms"
        const val QUERY_PARAM_FROM_SYMBOL = "fsyms"
    }
}