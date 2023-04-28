package com.asad.currencyconverter.data.remote.models.latest

import com.google.gson.annotations.SerializedName

data class LatestExchangeRates(
    @SerializedName("base")
    val base: String,
    @SerializedName("date")
    val date: String,
    @SerializedName("rates")
    val rates: HashMap<String, Double>,
    @SerializedName("success")
    val success: Boolean,
    @SerializedName("timestamp")
    val timestamp: Int
)