package com.xihadulislam.kotlin_mvvm_daggerhiltboilerplate.di


import com.xihadulislam.kotlin_mvvm_daggerhiltboilerplate.BuildConfig
import com.xihadulislam.kotlin_mvvm_daggerhiltboilerplate.data.networks.service.ProductService
import com.xihadulislam.kotlin_mvvm_daggerhiltboilerplate.utils.EndPoints
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun provideOkHttpClient() = if (BuildConfig.DEBUG) {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()
    } else {
        OkHttpClient
            .Builder()
            .build()
    }

    @Singleton
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(EndPoints.NODE_BASE_URL)
        .client(okHttpClient)
        .build()


    @Provides
    @Singleton
    fun provideProductService(retrofit: Retrofit): ProductService =
        retrofit.create(ProductService::class.java)


}