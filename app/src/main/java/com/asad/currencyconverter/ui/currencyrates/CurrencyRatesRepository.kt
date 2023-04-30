package com.asad.currencyconverter.ui.currencyrates

import com.asad.currencyconverter.data.manager.AppDataManager
import javax.inject.Inject

class CurrencyRatesRepository @Inject constructor(private val dataManager: AppDataManager) {

    fun fetchCurrencyRatesFromDb() = dataManager.getAppDatabaseHelper().getCurrencyRatesList()

}