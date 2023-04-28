package com.asad.currencyconverter.utils

import com.asad.currencyconverter.BuildConfig
import timber.log.Timber
import timber.log.Timber.DebugTree

/**
 * @Author: Asad Mukhtar
 * @Date: 25/04/23
 */
fun debugLog(tag: String, message: String) {
    Timber.tag(tag).d(message)
}

fun debugLog(tag: String, message: String, vararg objects: Any) {
    Timber.tag(tag).d(message, *objects)
}

fun errorLog(tag: String, error: String) {
    Timber.tag(tag).e(error)
}

fun errorLog(message: String, throwable: Throwable, vararg objects: Any) {
    Timber.e(throwable, message, *objects)
}

fun initTimber() {
    if (BuildConfig.DEBUG) {
        Timber.plant(DebugTree())
    }
}