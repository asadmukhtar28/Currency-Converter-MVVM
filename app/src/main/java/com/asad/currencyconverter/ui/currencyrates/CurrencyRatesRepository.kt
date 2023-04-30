package com.asad.currencyconverter.ui.currencyrates

import com.asad.currencyconverter.data.manager.AppDataManager
import com.asad.currencyconverter.data.remote.BaseRepository
import javax.inject.Inject

class CurrencyRatesRepository @Inject constructor(dataManager: AppDataManager) :
    BaseRepository(dataManager) {

    fun fetchCurrencyRatesFromDb() = dataManager.getAppDatabaseHelper().getCurrencyRatesList()

}