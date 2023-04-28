package com.asad.currencyconverter.data.remote.models.latest

import com.google.gson.annotations.SerializedName

data class CurrencySymbols(
    @SerializedName("success")
    val success: Boolean,
    @SerializedName("symbols")
    val symbols: HashMap<String, String>
)