package com.asad.currencyconverter.data.remote.helper

import com.asad.currencyconverter.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import java.util.concurrent.TimeUnit

/**
 * @Author: Asad Mukhtar
 * @Date: 25/04/23
 */

object HttpClient {
    fun getHTTPClient(): OkHttpClient {
        val httpClient = OkHttpClient.Builder().connectTimeout(40, TimeUnit.SECONDS)
            .readTimeout(40, TimeUnit.SECONDS).writeTimeout(40, TimeUnit.SECONDS)

        httpClient.addInterceptor { chain ->
            val request = chain.request()
            val builder = request.newBuilder()

            builder.addHeader("apikey", BuildConfig.CURRENCY_EXCHANGE_API_KEY)

            chain.proceed(builder.build())
        }

        if (BuildConfig.DEBUG) {
            val logging = HttpLoggingInterceptor()
            logging.setLevel(HttpLoggingInterceptor.Level.BODY)
            httpClient.addInterceptor(logging)
        }

        return httpClient.build()
    }
}