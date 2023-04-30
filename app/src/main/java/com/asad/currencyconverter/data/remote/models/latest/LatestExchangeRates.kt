package com.asad.currencyconverter.data.remote.models.latest

import com.asad.currencyconverter.data.local.models.CurrencyRatesDbModel
import com.asad.currencyconverter.utils.convertDoubleTo2Decimal
import com.google.gson.annotations.SerializedName

data class LatestExchangeRates(
    @SerializedName("base") val base: String,
    @SerializedName("date") val date: String,
    @SerializedName("rates") val rates: HashMap<String, Double>,
    @SerializedName("success") val success: Boolean,
    @SerializedName("timestamp") val timestamp: Int
)

fun LatestExchangeRates.toCurrencyRatesDbModel() = this.rates.map { (key, value) ->
    CurrencyRatesDbModel(
        currencyName = key, currencyRate = value.convertDoubleTo2Decimal()
    )
}
