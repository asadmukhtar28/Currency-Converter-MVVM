package com.asad.currencyconverter.ui.navgraph

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.asad.currencyconverter.ui.MainActivityViewModel
import com.asad.currencyconverter.ui.currencyrates.CurrencyConverter
import com.asad.currencyconverter.ui.splash.SplashScreen
import kotlinx.coroutines.delay

@Composable
fun Navigation(viewModel: MainActivityViewModel) {
    val navController = rememberNavController()

    LaunchedEffect(Unit) {
        delay(500)
        navController.navigate(route = Screen.CurrencyRatesListScreen.route) {
            popUpTo(Screen.SplashScreen.route) {
                inclusive = true
            }
        }
    }

    NavHost(navController = navController, startDestination = Screen.SplashScreen.route) {
        composable(Screen.SplashScreen.route) {
            SplashScreen()
        }
        composable(route = Screen.CurrencyRatesListScreen.route) {
            CurrencyConverter(viewModel)
        }
    }
}