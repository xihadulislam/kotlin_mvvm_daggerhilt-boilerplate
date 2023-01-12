package com.xihadulislam.kotlin_mvvm_daggerhiltboilerplate.utils

import android.content.res.ColorStateList
import android.graphics.PorterDuff
import android.graphics.PorterDuffColorFilter
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.ColorInt
import androidx.annotation.ColorRes
import androidx.core.content.ContextCompat
import androidx.core.widget.ImageViewCompat

object ColorUtils {

    fun TextView.setDrawableColor(@ColorRes color: Int) {
        compoundDrawablesRelative.filterNotNull().forEach {
            it.colorFilter = PorterDuffColorFilter(
                ContextCompat.getColor(this.context, color),
                PorterDuff.Mode.SRC_IN
            )
        }
    }

    fun ImageView.setTint(@ColorInt color: Int?) {
        if (color == null) {
            ImageViewCompat.setImageTintList(this, null)
            return
        }
        ImageViewCompat.setImageTintMode(this, PorterDuff.Mode.SRC_ATOP)
        ImageViewCompat.setImageTintList(this, ColorStateList.valueOf(color))
    }

}