package com.xihadulislam.kotlin_mvvm_daggerhiltboilerplate.di

import android.content.Context
import androidx.room.Room
import com.xihadulislam.kotlin_mvvm_daggerhiltboilerplate.data.db.AppDatabase
import com.xihadulislam.kotlin_mvvm_daggerhiltboilerplate.data.db.dao.ProductDAO
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object DatabaseModule {
    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext appContext: Context): AppDatabase {
        return Room.databaseBuilder(
            appContext,
            AppDatabase::class.java,
            "app_database"
        ).fallbackToDestructiveMigration().build()
    }

    @Provides
    fun provideOrderDAO(appDatabase: AppDatabase): ProductDAO {
        return appDatabase.productDAO
    }


}