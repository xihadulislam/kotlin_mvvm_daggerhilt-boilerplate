package com.xihadulislam.kotlin_mvvm_daggerhiltboilerplate.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.xihadulislam.kotlin_mvvm_daggerhiltboilerplate.data.db.converters.DynamicConverter
import com.xihadulislam.kotlin_mvvm_daggerhiltboilerplate.data.db.converters.IntListConverter
import com.xihadulislam.kotlin_mvvm_daggerhiltboilerplate.data.db.converters.StringListConverter
import com.xihadulislam.kotlin_mvvm_daggerhiltboilerplate.data.db.dao.ProductDAO
import com.xihadulislam.kotlin_mvvm_daggerhiltboilerplate.data.db.entities.ProductEntities


@Database(
    entities = [
        ProductEntities::class,
    ],
    version = 1,
    exportSchema = false
)
@TypeConverters(
    DynamicConverter::class,
    StringListConverter::class,
    IntListConverter::class
)

abstract class AppDatabase : RoomDatabase() {

    abstract val productDAO: ProductDAO
}
