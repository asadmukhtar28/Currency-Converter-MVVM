package com.asad.currencyconverter.data.manager

import com.asad.currencyconverter.data.local.helper.CurrencyAppDbHelper
import com.asad.currencyconverter.data.remote.api.CurrencyExchangeApi

interface AppDataManager {
    fun getApiHelper(): CurrencyExchangeApi
    fun getAppDatabaseHelper(): CurrencyAppDbHelper
}