package com.xihadulislam.kotlin_mvvm_daggerhiltboilerplate.utils

import android.os.SystemClock
import android.view.View

object AppUtil {
    private var mLastClickTime = 0L

    fun isOpenRecently(): Boolean {
        if (SystemClock.elapsedRealtime() - mLastClickTime < 1000) {
            return true
        }
        mLastClickTime = SystemClock.elapsedRealtime()
        return false
    }


    class OnClickListenerThrottled(private val block: () -> Unit, private val wait: Long) : View.OnClickListener {

        private var lastClickTime = 0L

        override fun onClick(view: View) {
            if (SystemClock.elapsedRealtime() - lastClickTime < wait) {
                return
            }
            lastClickTime = SystemClock.elapsedRealtime()

            block()
        }
    }

    /**
     * A throttled click listener that only invokes [block] at most once per every [wait] milliseconds.
     */
    fun View.setOnSingleClickListener(wait: Long = 800L, block: () -> Unit) {
        setOnClickListener(OnClickListenerThrottled(block, wait))
    }





}