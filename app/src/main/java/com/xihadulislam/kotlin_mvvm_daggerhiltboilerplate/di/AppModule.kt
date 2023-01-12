package com.xihadulislam.kotlin_mvvm_daggerhiltboilerplate.di

import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
//
//    @Provides
//    @Singleton
//    fun provideKPSettings(@ApplicationContext appContext: Context): KPSettings =
//        KPSettings(appContext)
//
//    @Provides
//    @Singleton
//    fun provideAppSharedPref(@ApplicationContext appContext: Context): AppSharedPref =
//        AppSharedPref(provideKPSettings(appContext))
}