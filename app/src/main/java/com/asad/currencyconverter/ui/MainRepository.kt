package com.asad.currencyconverter.ui

import com.asad.currencyconverter.data.manager.AppDataManager
import javax.inject.Inject

class MainRepository @Inject constructor(private val dataManager: AppDataManager) {

    fun fetchCurrencyRatesFromDb() = dataManager.getAppDatabaseHelper().getCurrencyRatesList()

}