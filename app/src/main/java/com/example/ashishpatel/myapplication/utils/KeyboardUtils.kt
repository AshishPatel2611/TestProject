package com.example.ashishpatel.myapplication.utils

import android.app.Activity
import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager

/**
 * Created by ashish on 13/2/18.
 */
object KeyboardUtils {

    fun hideKeyboard(context: Activity, view: View) {
        val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view.windowToken, 0);
    }

}