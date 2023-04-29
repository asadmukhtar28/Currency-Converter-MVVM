package com.asad.currencyconverter.ui.currencyrates

import com.asad.currencyconverter.data.manager.AppDataManager
import com.asad.currencyconverter.data.remote.BaseRepository
import com.asad.currencyconverter.data.remote.helper.NetworkResult
import com.asad.currencyconverter.data.remote.models.latest.CurrencySymbols
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class CurrencyRatesRepository @Inject constructor(dataManager: AppDataManager) :
    BaseRepository(dataManager) {

    suspend fun fetchCurrencySymbols(): Flow<NetworkResult<CurrencySymbols>> {
        return flow {
            val response = makeApiCall { dataManager.getApiHelper().fetchCurrencySymbols() }
            emit(response)
        }.flowOn(Dispatchers.IO)
    }
}