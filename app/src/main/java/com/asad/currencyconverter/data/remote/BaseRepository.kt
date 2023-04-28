package com.asad.currencyconverter.data.remote

import com.asad.currencyconverter.CurrencyConverterApp
import com.asad.currencyconverter.data.manager.AppDataManager
import com.asad.currencyconverter.data.remote.helper.NetworkResult
import retrofit2.HttpException
import retrofit2.Response

open class BaseRepository(val dataManager: AppDataManager) {

    suspend fun <T : Any> makeApiCall(
        apiCall: suspend () -> Response<T>,
    ): NetworkResult<T> {
        return try {
            if (isNetworkNotAvailable()) {
                val response = apiCall.invoke()
                val body = response.body()
                if (response.isSuccessful && body != null) {
                    NetworkResult.Success(body)
                } else {
                    NetworkResult.Error(response.code(), response.message())
                }
            } else {
                return NetworkResult.Error(1, ""/*e.code(), e.message()*/)
            }
        } catch (e: HttpException) {
            NetworkResult.Error(e.code(), e.message())
        }
    }

    private fun isNetworkNotAvailable(): Boolean {
        return CurrencyConverterApp.isNetworkConnected
    }

}