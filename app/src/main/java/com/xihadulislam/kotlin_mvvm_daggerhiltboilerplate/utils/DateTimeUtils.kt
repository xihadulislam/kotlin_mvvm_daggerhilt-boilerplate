package com.epos.turnpos.utils

import android.annotation.SuppressLint
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

object DateTimeUtils {


    @SuppressLint("SimpleDateFormat")
    fun getDateTime(): String {
        val df: DateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
        return df.format(Calendar.getInstance().time)
    }

}