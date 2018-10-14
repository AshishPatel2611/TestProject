package com.example.ashishpatel.myapplication.utils

import android.content.Context
import android.support.design.widget.Snackbar
import android.util.Log
import android.view.View
import android.widget.TextView
import android.widget.Toast
import com.example.ashishpatel.myapplication.R


/**
 * Created by ashish on 6/2/18.
 */
object SnackbarUtils {

    /* fun showSnackBar(view: View, message: String, isUndoButtonRequired: Boolean) {
         val snackbar = Snackbar.make(view, message, Snackbar.LENGTH_SHORT)
         if (isUndoButtonRequired) {
             snackbar.setAction("Undo", View.OnClickListener {
                 val snackbar1 = Snackbar.make(view, "Message is restored!", Snackbar.LENGTH_SHORT)
                 snackbar1.show()
             }).setActionTextColor(Color.WHITE)
         }
         snackbar.show()
     }
 */
    fun success(context: Context, view: View, message: String) {
        try {
            val snackbar = Snackbar.make(view, message, Snackbar.LENGTH_SHORT)
            snackbar.view.setBackgroundColor(context.resources.getColor(R.color.success))
            // change snackbar text color
            val snackbarTextId = android.support.design.R.id.snackbar_text
            val textView = snackbar.view.findViewById(snackbarTextId) as TextView
            textView.setTextColor(context.resources.getColor(R.color.black))
            snackbar.show()
        } catch (e: IllegalArgumentException) {
            Log.e("SnackbarUtils", "success : ${e.printStackTrace()} ")
        }
    }

    fun warning(context: Context, view: View, message: String) {
        try {
            val snackbar = Snackbar.make(view, message, Snackbar.LENGTH_SHORT)
            snackbar.view.setBackgroundColor(context.resources.getColor(R.color.warning))
            // change snackbar text color
            val snackbarTextId = android.support.design.R.id.snackbar_text
            val textView = snackbar.view.findViewById(snackbarTextId) as TextView
            textView.setTextColor(context.resources.getColor(R.color.black))
            snackbar.show()
        } catch (e: IllegalArgumentException) {
            Log.e("SnackbarUtils", "warning : ${e.toString()} ")
        }
    }

    fun error(context: Context, view: View, message: String) {
        try {
            val snackbar = Snackbar.make(view, message, Snackbar.LENGTH_SHORT)
            snackbar.view.setBackgroundColor(context.resources.getColor(R.color.error))
            // change snackbar text color
            val snackbarTextId = android.support.design.R.id.snackbar_text
            val textView = snackbar.view.findViewById(snackbarTextId) as TextView
            textView.setTextColor(context.resources.getColor(R.color.black))
            snackbar.show()
        } catch (e: IllegalArgumentException) {
            Log.e("SnackbarUtils", "error : ${e.toString()} ")
        }
    }


    /* fun showToast(context: Context, message: String) {
         Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
     }

     fun showLongToast(context: Context, message: String) {
         Toast.makeText(context, message, Toast.LENGTH_LONG).show()
     }

    */

   /* fun showErrorToast(context: Context, message: String, toast_error: View) {
        val toast = Toast.makeText(context, message, Toast.LENGTH_SHORT)
        toast.setText(message)
        toast.view = toast_error
        toast_error.txtToast.text = message
        toast.duration = Toast.LENGTH_SHORT
        toast.show()
    }

    fun showSuccessToast(context: Context, message: String, toast_success: View) {
        val toast = Toast.makeText(context, message, Toast.LENGTH_SHORT)
        toast.setText(message)
        toast.view = toast_success
        toast_success.txtToast.text = message
        toast.duration = Toast.LENGTH_SHORT
        toast.show()
    }*/
}