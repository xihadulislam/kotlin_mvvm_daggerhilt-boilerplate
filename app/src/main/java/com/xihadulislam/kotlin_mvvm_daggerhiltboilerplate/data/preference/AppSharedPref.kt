package com.xihadulislam.kotlin_mvvm_daggerhiltboilerplate.data.preference

import com.xihadulislam.kotlin_mvvm_daggerhiltboilerplate.utils.AppConstants.DEVICE_ID
import com.xihadulislam.kotlin_mvvm_daggerhiltboilerplate.utils.EndPoints
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AppSharedPref @Inject constructor(private val kpSettings: KPSettings) {

    fun setAuthToken(token: String) {
        kpSettings.setStringValue(EndPoints.AUTH_TOKEN, token)
    }

    fun getAuthToken(): String {
        return kpSettings.getStringValue(EndPoints.AUTH_TOKEN, "")
    }

    fun getDeviceId(): String {
        return kpSettings.getStringValue(DEVICE_ID, "")
    }

    fun setDeviceId(deviceID: String) {
        kpSettings.setStringValue(DEVICE_ID, deviceID)
    }


}