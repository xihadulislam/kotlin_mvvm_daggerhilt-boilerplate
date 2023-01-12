package com.xihadulislam.kotlin_mvvm_daggerhiltboilerplate.utils

import android.content.res.Resources
import android.graphics.Rect
import android.view.View
import android.view.ViewGroup
import android.view.ViewTreeObserver


object ViewUtils {

        private const val TAG = "LayoutUtils"

        fun getHeight(layout: View): Int {
            var height: Int = 0
            var width: Int = 0
            val vto: ViewTreeObserver = layout.viewTreeObserver
            vto.addOnGlobalLayoutListener(object : ViewTreeObserver.OnGlobalLayoutListener {
                override fun onGlobalLayout() {
                    layout.viewTreeObserver.removeOnGlobalLayoutListener(this)
                    width = layout.measuredWidth
                    height = layout.measuredHeight

                }
            })

            return height

        }


        inline fun View.getDimensions(crossinline onDimensionsReady: (Int, Int) -> Unit) {
            lateinit var layoutListener: ViewTreeObserver.OnGlobalLayoutListener
            layoutListener = ViewTreeObserver.OnGlobalLayoutListener {
                viewTreeObserver.removeOnGlobalLayoutListener(layoutListener)
                onDimensionsReady(width, height)
            }
            viewTreeObserver.addOnGlobalLayoutListener(layoutListener)
        }

        fun View?.removeSelf() {
            this ?: return
            val parent = parent as? ViewGroup ?: return
            parent.removeView(this)
        }


        fun View?.addTo(parent: ViewGroup?) {
            this ?: return
            parent ?: return
            parent.addView(this)
        }

        // Null-safe extension
        fun ViewGroup?.addView(view: View?) {
            this ?: return
            view ?: return
            addView(view)
        }


        inline fun View?.onSizeChange(crossinline runnable: (h: Int) -> Unit) = this?.apply {
            addOnLayoutChangeListener { _, left, top, right, bottom, oldLeft, oldTop, oldRight, oldBottom ->
                val rect = Rect(left, top, right, bottom)
                val oldRect = Rect(oldLeft, oldTop, oldRight, oldBottom)
                if (rect.width() != oldRect.width() || rect.height() != oldRect.height()) {
                    runnable(rect.height());
                }
            }
        }


        fun isViewVisible(view: View?): Boolean {
            if (view == null) {
                return false
            }
            val rect = Rect()
            return view.getGlobalVisibleRect(rect)
        }

        fun onScreen(view: View): Boolean {
            val visibleRect = Rect()
            view.getGlobalVisibleRect(visibleRect)
            return visibleRect.height() == view.height && visibleRect.width() == view.width
        }

        fun View.isVisibleView(): Boolean {

            val actualPosition = Rect()
            val isGlobalVisible = getGlobalVisibleRect(actualPosition)
            val screenWidth = Resources.getSystem().displayMetrics.widthPixels
            val screenHeight = Resources.getSystem().displayMetrics.heightPixels
            val screen = Rect(0, 0, screenWidth, screenHeight)
            return isGlobalVisible && Rect.intersects(actualPosition, screen)
        }



}