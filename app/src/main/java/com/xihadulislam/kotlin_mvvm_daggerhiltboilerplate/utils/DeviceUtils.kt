package com.xihadulislam.kotlin_mvvm_daggerhiltboilerplate.utils

import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import android.provider.Settings
import android.telephony.TelephonyManager
import android.text.TextUtils
import com.xihadulislam.kotlin_mvvm_daggerhiltboilerplate.data.preference.AppSharedPref

object DeviceUtils {

    @SuppressLint("HardwareIds")
    fun getDeviceId(context: Context, appSharedPref: AppSharedPref) {

        try {
            var deviceId = Settings.Secure.getString(
                context.applicationContext.contentResolver,
                Settings.Secure.ANDROID_ID
            )
            appSharedPref.setDeviceId(deviceId)

            if (Build.MANUFACTURER.equals("Rockchip", ignoreCase = true)) {
                val brand: String = getDeviceName()
                if (brand.contains("rk3288")) { // y33
                    appSharedPref.setDeviceId(deviceId)
                } else {
                    deviceId = getIMEI(context)
                    appSharedPref.setDeviceId(deviceId)
                }
            } else {
                appSharedPref.setDeviceId(deviceId)
            }
        } catch (e: Exception) {
            //appSharedPref.setDeviceId("")
        }
    }


    fun getDeviceName(): String {
        val manufacturer = Build.MANUFACTURER
        val model = Build.MODEL
        return if (model.startsWith(manufacturer)) {
            capitalize(model)
        } else capitalize(manufacturer) + " " + model
    }

    private fun capitalize(str: String): String {
        if (TextUtils.isEmpty(str)) {
            return str
        }
        val arr = str.toCharArray()
        var capitalizeNext = true
        val phrase = StringBuilder()
        for (c in arr) {
            if (capitalizeNext && Character.isLetter(c)) {
                phrase.append(Character.toUpperCase(c))
                capitalizeNext = false
                continue
            } else if (Character.isWhitespace(c)) {
                capitalizeNext = true
            }
            phrase.append(c)
        }
        return phrase.toString()
    }


    @SuppressLint("HardwareIds")
    fun getIMEI(context: Context): String {
        val tm = context.getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager
        return tm.deviceId
    }


    fun getVersionName(context: Context): String {
        try {
            val pInfo = context.packageManager.getPackageInfo(context.packageName, 0)
            return pInfo.versionName
        } catch (e: PackageManager.NameNotFoundException) {
            e.printStackTrace()
        }

        return ""
    }

    fun getPushTokenAction(): String {
        return if (Build.MANUFACTURER.equals("Rockchip", ignoreCase = true)) {
            val brand: String = getDeviceName()
            if (brand.contains("rk3288")) "pushy" //y33
            else "firebase"
        } else "firebase"

    }

}