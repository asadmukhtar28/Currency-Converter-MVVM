package com.asad.currencyconverter.utils

import java.util.Locale

fun Double.convertDoubleTo2Decimal(): Double {
    return String.format(Locale.ENGLISH, "%.2f", this).toDouble()
}