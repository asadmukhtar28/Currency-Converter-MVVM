package com.asad.currencyconverter.data.remote.api

import com.asad.currencyconverter.data.remote.models.latest.CurrencySymbols
import com.asad.currencyconverter.data.remote.models.latest.LatestExchangeRates
import retrofit2.Response
import retrofit2.http.GET

interface CurrencyExchangeApi {

    @GET("latest")
    suspend fun fetchLatestCurrencyRates(): Response<LatestExchangeRates>

    @GET("symbols")
    suspend fun fetchCurrencySymbols(): Response<CurrencySymbols>
}