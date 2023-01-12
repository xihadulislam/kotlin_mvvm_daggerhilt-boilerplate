package com.epos.turnpos.utils

import android.content.Context
import android.util.Log
import java.io.IOException


object Utils {

    private const val TAG = "Utils"


    fun getJsonDataFromAsset(context: Context, fileName: String): String? {
        val jsonString: String
        try {
            jsonString = context.assets.open(fileName).bufferedReader().use { it.readText() }
            Log.d(TAG, "getJsonDataFromAsset: $jsonString")
        } catch (ioException: IOException) {
            Log.e(TAG, "getJsonDataFromAsset: ", ioException)
            ioException.printStackTrace()
            return null
        }
        return jsonString
    }


}

