package com.asad.currencyconverter.data.manager

import com.asad.currencyconverter.data.remote.api.CurrencyExchangeApi
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AppDataManagerImpl @Inject constructor(private val apiService: CurrencyExchangeApi) :
    AppDataManager {
    override fun getApiHelper(): CurrencyExchangeApi {
        return apiService
    }
}