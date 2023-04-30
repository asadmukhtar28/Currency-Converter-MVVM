package com.asad.currencyconverter.data.local.helper

import com.asad.currencyconverter.data.local.CurrencyAppDatabase
import com.asad.currencyconverter.data.local.models.CurrencyRatesDbModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
open class CurrencyAppDbHelperImpl @Inject constructor(private val appDatabase: CurrencyAppDatabase) :
    CurrencyAppDbHelper {
    override fun saveCurrencyRatesList(currencyRates: List<CurrencyRatesDbModel>) {
        appDatabase.currencyExchangeDao().saveCurrencyRatesList(currencyRates)
    }

    override fun getCurrencyRatesList(): Flow<List<CurrencyRatesDbModel>> {
        return appDatabase.currencyExchangeDao().getCurrencyRatesList()
    }
}