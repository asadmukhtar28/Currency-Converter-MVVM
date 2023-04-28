package com.asad.currencyconverter.utils.network

import kotlinx.coroutines.flow.Flow

interface NetworkConnectivityObserver {
    fun observer(): Flow<NetworkStatus>
}