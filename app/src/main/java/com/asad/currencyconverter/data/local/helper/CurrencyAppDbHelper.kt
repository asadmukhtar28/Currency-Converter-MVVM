package com.asad.currencyconverter.data.local.helper

import com.asad.currencyconverter.data.local.models.CurrencyRatesDbModel
import kotlinx.coroutines.flow.Flow

interface CurrencyAppDbHelper {
    fun saveCurrencyRatesList(currencyRates: List<CurrencyRatesDbModel>)
    fun getCurrencyRatesList(): Flow<List<CurrencyRatesDbModel>>
}