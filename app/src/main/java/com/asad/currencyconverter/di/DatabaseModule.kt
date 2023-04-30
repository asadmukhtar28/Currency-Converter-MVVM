package com.asad.currencyconverter.di

import android.content.Context
import com.asad.currencyconverter.data.local.CurrencyAppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class DatabaseModule {

    @Singleton
    @Provides
    fun provideCurrencyAppDatabase(@ApplicationContext context: Context) =
        CurrencyAppDatabase.getInstance(context)
}