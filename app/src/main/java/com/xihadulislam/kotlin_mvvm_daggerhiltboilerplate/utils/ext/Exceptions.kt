package com.xihadulislam.kotlin_mvvm_daggerhiltboilerplate.utils.ext

import java.io.IOException

class ApiException(message: String) : IOException(message)

class RetroApiException(message: String) : IOException(message) {
    fun getErrorCode(): Int {
        val match = Regex("Error Code: (\\d+)").find(this.message.toString())
        return match?.groupValues?.get(1)?.toInt() ?: -1
    }
}

class NoInternetException(message: String) : IOException(message)