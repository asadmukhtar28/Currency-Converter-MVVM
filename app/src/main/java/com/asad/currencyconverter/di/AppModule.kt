package com.asad.currencyconverter.di

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
}