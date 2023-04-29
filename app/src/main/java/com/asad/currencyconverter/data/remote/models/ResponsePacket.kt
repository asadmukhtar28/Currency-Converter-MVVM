package com.asad.currencyconverter.data.remote.models

import com.google.gson.annotations.SerializedName

class ResponsePacket<T> {
    @SerializedName("data")
    var data: T? = null

    @SerializedName("message")
    var message: String? = null

    @SerializedName("statusCode")
    var statusCode: Int? = 200

}