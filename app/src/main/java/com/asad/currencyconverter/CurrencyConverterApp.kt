package com.asad.currencyconverter

import android.app.Application
import androidx.hilt.work.HiltWorkerFactory
import androidx.work.Configuration
import androidx.work.Constraints
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.NetworkType
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import com.asad.currencyconverter.data.manager.AppDataManager
import com.asad.currencyconverter.utils.initTimber
import com.asad.currencyconverter.utils.network.NetworkConnectivityObserverImpl
import com.asad.currencyconverter.utils.network.NetworkStatus
import com.asad.currencyconverter.worker.ExchangeCurrencyWorker
import dagger.hilt.android.HiltAndroidApp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.concurrent.TimeUnit
import javax.inject.Inject

@HiltAndroidApp
class CurrencyConverterApp : Application(), Configuration.Provider {

    @Inject
    lateinit var workerFactory: HiltWorkerFactory

    @Inject
    lateinit var appDataManager: AppDataManager

    private val constraint by lazy {
        Constraints.Builder()
            .setRequiredNetworkType(NetworkType.CONNECTED)
            .build()
    }

    private val worker by lazy {
        PeriodicWorkRequestBuilder<ExchangeCurrencyWorker>(1, TimeUnit.MINUTES)
            .setConstraints(constraint)
            .setInitialDelay(0, TimeUnit.MILLISECONDS)
            .build()
    }

    override fun onCreate() {
        super.onCreate()
        initTimber()
        observeInternetConnectivity()
        scheduleWorkManager()
    }

    private fun observeInternetConnectivity() {
        CoroutineScope(Dispatchers.IO).launch {
            NetworkConnectivityObserverImpl(this@CurrencyConverterApp).observer().collect {
                isNetworkConnected = when (it) {
                    NetworkStatus.Connected -> true
                    NetworkStatus.Disconnected -> false
                }
            }
        }
    }

    private fun scheduleWorkManager() {
        WorkManager.getInstance(this)
            .enqueueUniquePeriodicWork("", ExistingPeriodicWorkPolicy.KEEP, worker)
    }

    override fun getWorkManagerConfiguration() =
        Configuration.Builder().setWorkerFactory(workerFactory)
            .setMinimumLoggingLevel(android.util.Log.DEBUG).build()

    companion object {
        var isNetworkConnected = false
    }
}