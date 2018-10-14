package com.example.ashishpatel.myapplication.utils

import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup

/**
 * Created by ashish on 10/3/18.
 */
object LayoutUtils {
    fun disableViewGroup(layout: ViewGroup) {
        layout.isEnabled = false
        for (i in 0 until layout.childCount) {
            val child = layout.getChildAt(i)
            if (child is ViewGroup) {
                disableViewGroup(child)
            } else {
                child.isEnabled = false
            }
        }
    }

    fun enableViewGroup(layout: ViewGroup) {
        layout.isEnabled = true
        for (i in 0 until layout.childCount) {
            val child = layout.getChildAt(i)
            if (child is ViewGroup) {
                enableViewGroup(child)
            } else {
                child.isEnabled = true
            }
        }
    }

    fun hideViewGroup(layout: ViewGroup) {
        layout.visibility = GONE
        for (i in 0 until layout.childCount) {
            val child = layout.getChildAt(i)
            if (child is ViewGroup) {
                hideViewGroup(child)
            } else {
                child.visibility = GONE
            }
        }
    }

    fun showViewGroup(layout: ViewGroup) {
        layout.visibility = VISIBLE
        for (i in 0 until layout.childCount) {
            val child = layout.getChildAt(i)
            if (child is ViewGroup) {
                hideViewGroup(child)
            } else {
                child.visibility = VISIBLE
            }
        }
    }
}