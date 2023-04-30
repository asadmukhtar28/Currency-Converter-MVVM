package com.asad.currencyconverter.ui.navgraph

sealed class Screen(val route: String) {
    object SplashScreen : Screen("SplashScreen")
    object CurrencyRatesListScreen : Screen("CurrencyRatesListScreen")
}