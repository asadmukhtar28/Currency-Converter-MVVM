package com.asad.currencyconverter.utils.network

sealed class NetworkStatus {
    object Disconnected : NetworkStatus()
    object Connected : NetworkStatus()
}