package com.xihadulislam.kotlin_mvvm_daggerhiltboilerplate.data.db.converters

import androidx.room.TypeConverter

object DynamicConverter {
    @TypeConverter
    @JvmStatic
    fun toString(data: Any?): String? {
        return data?.toString()
    }

    @TypeConverter
    @JvmStatic
    fun toAny(data: String?): Any? = data
}