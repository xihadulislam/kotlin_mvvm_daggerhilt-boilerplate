package com.xihadulislam.kotlin_mvvm_daggerhiltboilerplate.utils.ext

import android.app.Activity
import android.content.Intent
import android.os.Handler
import android.os.Looper
import com.xihadulislam.kotlin_mvvm_daggerhiltboilerplate.screens.main.MainActivity


fun Activity.startMainActivity(miLis: Long) {
    Handler(Looper.getMainLooper()).postDelayed({
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }, miLis)
}
