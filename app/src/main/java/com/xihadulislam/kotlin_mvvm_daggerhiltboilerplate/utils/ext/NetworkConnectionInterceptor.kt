package com.xihadulislam.kotlin_mvvm_daggerhiltboilerplate.utils.ext

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class NetworkConnectionInterceptor(context: Context) : Interceptor {

    companion object {
        @Provides
        @Singleton
        fun provideNetworkConnectionInterceptor(@ApplicationContext appContext: Context): NetworkConnectionInterceptor =
            NetworkConnectionInterceptor(appContext)
    }


    private val applicationContext = context.applicationContext

    override fun intercept(chain: Interceptor.Chain): Response {
        if (!isInternetAvailable()) {
            throw NoInternetException("Can't Connect! Check your internet connection and retry.")
        }
        return chain.proceed(chain.request())
    }

    fun isInternetAvailable(): Boolean {
        val connectivityManager =
            applicationContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        var result = false

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {

            val networkCapabilities = connectivityManager.activeNetwork ?: return false
            val actNw =
                connectivityManager.getNetworkCapabilities(networkCapabilities) ?: return false
            result = when {
                actNw.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                actNw.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                actNw.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
                else -> false
            }
        } else {
            connectivityManager.run {
                connectivityManager.activeNetworkInfo?.run {
                    result = when (type) {
                        ConnectivityManager.TYPE_WIFI -> true
                        ConnectivityManager.TYPE_MOBILE -> true
                        ConnectivityManager.TYPE_ETHERNET -> true
                        else -> false
                    }
                }
            }
        }

        return result
    }
}