package com.asad.currencyconverter.data.local.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.asad.currencyconverter.utils.Constants

@Entity(tableName = Constants.CURRENCY_RATES_TABLE)
data class CurrencyRatesDbModel constructor(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    var currencyName: String = "",
    var currencyRate: Double = 0.0
)