package com.asad.currencyconverter.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.asad.currencyconverter.data.local.models.CurrencyRatesDbModel
import com.asad.currencyconverter.utils.Constants
import kotlinx.coroutines.flow.Flow

@Dao
abstract class CurrencyExchangeDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun saveCurrencyRatesList(currencySymbols: List<CurrencyRatesDbModel>)

    @Query("SELECT *FROM ${Constants.CURRENCY_RATES_TABLE}")
    abstract fun getCurrencyRatesList(): Flow<List<CurrencyRatesDbModel>>
}