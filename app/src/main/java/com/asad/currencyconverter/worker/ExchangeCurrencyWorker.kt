package com.asad.currencyconverter.worker

import android.content.Context
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.asad.currencyconverter.data.manager.AppDataManager
import com.asad.currencyconverter.data.remote.models.latest.toCurrencyRatesDbModel
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject

@HiltWorker
class ExchangeCurrencyWorker @AssistedInject constructor(
    @Assisted context: Context,
    @Assisted workerParams: WorkerParameters,
    private val dataManager: AppDataManager
) : CoroutineWorker(context, workerParams) {
    override suspend fun doWork(): Result {
        val response = dataManager.getApiHelper().fetchLatestCurrencyRates()
        if (response.isSuccessful && response.body() != null) {
            dataManager.getAppDatabaseHelper()
                .saveCurrencyRatesList(response.body()!!.toCurrencyRatesDbModel())
        }
        return Result.success()
    }
}