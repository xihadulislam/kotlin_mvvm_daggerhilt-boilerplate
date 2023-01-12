package com.xihadulislam.kotlin_mvvm_daggerhiltboilerplate.utils.ext

import com.xihadulislam.kotlin_mvvm_daggerhiltboilerplate.data.preference.AppSharedPref
import com.xihadulislam.kotlin_mvvm_daggerhiltboilerplate.utils.EndPoints
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject


class AuthInterceptor @Inject constructor(private val appSharedPref: AppSharedPref) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val requestBuilder = chain.request().newBuilder()

        if (appSharedPref.getAuthToken().isNotEmpty()) {
            requestBuilder.addHeader(
                "Authorization",
                "${EndPoints.SERVER_AUTH_HEADER} ${appSharedPref.getAuthToken()}"
            )
        }

        return chain.proceed(requestBuilder.build())
    }
}
