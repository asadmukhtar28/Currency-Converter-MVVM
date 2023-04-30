package com.asad.currencyconverter.data.manager

import com.asad.currencyconverter.data.local.helper.CurrencyAppDbHelper
import com.asad.currencyconverter.data.remote.api.CurrencyExchangeApi
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AppDataManagerImpl @Inject constructor(
    val apiService: CurrencyExchangeApi,
    val appDbHelper: CurrencyAppDbHelper
) : AppDataManager {
    override fun getApiHelper() = apiService

    override fun getAppDatabaseHelper(): CurrencyAppDbHelper {
        return appDbHelper
    }

}