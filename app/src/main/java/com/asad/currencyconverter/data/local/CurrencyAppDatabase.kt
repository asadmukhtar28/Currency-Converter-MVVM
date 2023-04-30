package com.asad.currencyconverter.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.asad.currencyconverter.data.local.dao.CurrencyExchangeDao
import com.asad.currencyconverter.data.local.models.CurrencyRatesDbModel
import com.asad.currencyconverter.utils.Constants

@Database(entities = [CurrencyRatesDbModel::class], version = 1, exportSchema = false)
abstract class CurrencyAppDatabase : RoomDatabase() {
    abstract fun currencyExchangeDao(): CurrencyExchangeDao

    companion object {
        @Volatile
        private var instance: CurrencyAppDatabase? = null

        fun getInstance(context: Context): CurrencyAppDatabase {
            return instance ?: synchronized(this) {
                buildDatabase(context).also {
                    instance = it
                }
            }
        }

        private fun buildDatabase(context: Context) = Room.databaseBuilder(
            context,
            CurrencyAppDatabase::class.java,
            Constants.CURRENCY_APP_DB_NAME
        ).build()

    }
}