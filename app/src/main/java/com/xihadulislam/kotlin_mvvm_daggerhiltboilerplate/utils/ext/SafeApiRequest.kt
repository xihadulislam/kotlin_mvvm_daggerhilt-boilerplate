package com.xihadulislam.kotlin_mvvm_daggerhiltboilerplate.utils.ext

import android.util.Log
import org.json.JSONException
import org.json.JSONObject
import retrofit2.Response

abstract class SafeApiRequest {

    companion object {
        private const val TAG = "SafeApiRequest"
    }

    suspend fun <T : Any> apiRequest(call: suspend () -> Response<T>): T {
        val response: Response<T>

        try {
            response = call.invoke()
        } catch (e: Exception) {
            if (e is NoInternetException) {
                throw NoInternetException(e.message.toString())
            } else {
                throw RetroApiException(e.message.toString())
            }
        }

        Log.d(TAG, "apiRequest: ${response.headers()}")
        val responseBody = response.body()
        if (response.isSuccessful && responseBody != null) return responseBody

        val error = response.errorBody().toString()
        val message = StringBuilder()

        error.let {
            try {
                message.append(JSONObject(it).getString("message"))
                message.append("\n")
            } catch (e: JSONException) {
            }
        }

        message.append("Error Code: ${response.code()}")
        throw RetroApiException(message.toString())
    }



}