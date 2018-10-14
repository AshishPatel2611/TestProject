package com.example.ashishpatel.myapplication.utils

import android.app.Activity
import android.app.ProgressDialog


/**
 * Created by ashish on 20/9/17.
 */

object ProgressDialogUtils {

    fun showProgressDialog(context: Activity, title: String?, message: String?, cancelable: Boolean): ProgressDialog {

        val alertDialog = createProgressDialog(context, title, message, cancelable)
        alertDialog.show()
        return alertDialog

    }

    private fun createProgressDialog(context: Activity, title: String?, message: String?, cancelable: Boolean): ProgressDialog {

        val alertDialog = ProgressDialog(context/*, R.style.DialogTheme*/)

        alertDialog.setTitle(title)
        alertDialog.setMessage(message)

        if (cancelable) {
            alertDialog.setCancelable(true)
        } else {
            alertDialog.setCancelable(false)
        }

        return alertDialog
    }


}
