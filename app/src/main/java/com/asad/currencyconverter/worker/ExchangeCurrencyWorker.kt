package com.asad.currencyconverter.worker

import android.content.Context
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.asad.currencyconverter.data.manager.AppDataManager
import com.asad.currencyconverter.utils.debugLog
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject

@HiltWorker
class ExchangeCurrencyWorker @AssistedInject constructor(
    @Assisted context: Context, @Assisted workerParams: WorkerParameters,
    val dataManager: AppDataManager
) : CoroutineWorker(context, workerParams) {
    override suspend fun doWork(): Result {
//        dataManager.getApiHelper().fetchLatestCurrencyRates()
        debugLog("asad_work", "~~~doWork~~~")
        return Result.success()
    }
}