package com.asad.currencyconverter.di

import android.app.Application
import android.content.Context
import com.asad.currencyconverter.data.local.helper.CurrencyAppDbHelper
import com.asad.currencyconverter.data.local.helper.CurrencyAppDbHelperImpl
import com.asad.currencyconverter.data.manager.AppDataManager
import com.asad.currencyconverter.data.manager.AppDataManagerImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class AppModule {
    @Provides
    @Singleton
    fun provideAppDataManager(dataManagerImpl: AppDataManagerImpl): AppDataManager {
        return dataManagerImpl
    }

    @Singleton
    @Provides
    fun provideContext(application: Application): Context {
        return application
    }

    @Singleton
    @Provides
    fun provideAppDbHelper(dbHelperImpl: CurrencyAppDbHelperImpl): CurrencyAppDbHelper {
        return dbHelperImpl
    }
}