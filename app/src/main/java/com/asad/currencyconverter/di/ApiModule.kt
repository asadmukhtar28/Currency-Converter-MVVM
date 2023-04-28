package com.asad.currencyconverter.di

import com.asad.currencyconverter.BuildConfig
import com.asad.currencyconverter.data.remote.api.CurrencyExchangeApi
import com.asad.currencyconverter.data.remote.helper.HttpClient
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object ApiModule {

    @Singleton
    @Provides
    fun provideRetrofitService(): CurrencyExchangeApi {
        val gson: Gson = GsonBuilder()
            .setLenient()
            .create()

        return Retrofit.Builder()
            .baseUrl(BuildConfig.CURRENCY_EXCHANGE_API_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .client(HttpClient.getHTTPClient())
            .build()
            .create(CurrencyExchangeApi::class.java)
    }


}