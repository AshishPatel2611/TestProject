package com.example.ashishpatel.myapplication.utils


import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.support.v4.app.DialogFragment
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.RelativeLayout
import com.example.ashishpatel.myapplication.MainActivity
import com.example.ashishpatel.myapplication.R

object AlertDialogUtil {

    fun showOneButtonAlertDialog(context: Context,
                                 title: String,
                                 message: String,
                                 positiveButton: String,
                                 onOneButtonAlertDialogClickListener: OnOneButtonAlertDialogClickListener): android.support.v7.app.AlertDialog {

        val alertDialog = AlertDialog.Builder(context).create()

        if (title.length > 0)
            alertDialog.setTitle(title)
        if (message.length > 0)
            alertDialog.setMessage(message)

        alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, positiveButton) { dialog, which ->
            onOneButtonAlertDialogClickListener.onPositiveButtonClicked(alertDialog)
        }


        return alertDialog
    }

    interface OnOneButtonAlertDialogClickListener {

        fun onPositiveButtonClicked(alertDialog: android.support.v7.app.AlertDialog)

    }

    fun showTwoButtonAlertDialog(context: Context,
                                 title: String,
                                 message: String,
                                 positiveButton: String,
                                 negativeButton: String,
                                 onTwoButtonAlertDialogClickListener: OnTwoButtonAlertDialogClickListener): android.support.v7.app.AlertDialog {


        val alertDialog = AlertDialog.Builder(context).create()

        if (title.length > 0)
            alertDialog.setTitle(title)
        if (message.length > 0)
            alertDialog.setMessage(message)

        alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, positiveButton) { dialog, which ->

            onTwoButtonAlertDialogClickListener.onPositiveButtonClicked(alertDialog)
        }
        alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, negativeButton) { dialog, which ->

            onTwoButtonAlertDialogClickListener.onNegativeButtonClicked(alertDialog)

        }

        return alertDialog
    }

    interface OnTwoButtonAlertDialogClickListener {

        fun onPositiveButtonClicked(alertDialog: android.support.v7.app.AlertDialog)

        fun onNegativeButtonClicked(alertDialog: android.support.v7.app.AlertDialog)
    }


    fun showThreeButtonAlertDialog(context: Context,
                                   title: String,
                                   message: String,
                                   positiveButton: String,
                                   negativeButton: String,
                                   neutralButton: String,
                                   onThreeButtonAlertDialogClickListener: OnThreeButtonAlertDialogClickListener): android.support.v7.app.AlertDialog {


        val alertDialog = AlertDialog.Builder(context).create()

        if (title.length > 0)
            alertDialog.setTitle(title)
        if (message.length > 0)
            alertDialog.setMessage(message)

        alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, positiveButton) { dialog, which ->

            onThreeButtonAlertDialogClickListener.onPositiveButtonClicked(alertDialog)
        }
        alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, negativeButton) { dialog, which ->

            onThreeButtonAlertDialogClickListener.onNegativeButtonClicked(alertDialog)

        }
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, neutralButton) { dialog, which ->

            onThreeButtonAlertDialogClickListener.onNeutralButtonClicked(alertDialog)

        }

        return alertDialog
    }

    interface OnThreeButtonAlertDialogClickListener {

        fun onPositiveButtonClicked(alertDialog: android.support.v7.app.AlertDialog)

        fun onNegativeButtonClicked(alertDialog: android.support.v7.app.AlertDialog)

        fun onNeutralButtonClicked(alertDialog: android.support.v7.app.AlertDialog)
    }

    fun createCustomAlertDialog(context: Context, customView: View): android.support.v7.app.AlertDialog {

        val alertDialog = AlertDialog.Builder(context).create()

        alertDialog.setView(customView)

        return alertDialog
    }

    fun setFullScreenDialog(context: Context): Dialog {
        // the content
        val root = RelativeLayout(context)
        root.layoutParams = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
        // creating the fullscreen dialog
        val dialog = Dialog(context, R.style.AppTheme)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(root)
        dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.WHITE))
        dialog.window!!.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)

        return dialog
    }


    fun showDialogFragment(activity: AppCompatActivity, fragment: DialogFragment) {

        try {
            val ft = activity.supportFragmentManager.beginTransaction()
            val prev = activity.supportFragmentManager.findFragmentByTag(fragment::class.java.simpleName)
            if (prev != null) {
                ft.remove(prev)
            }
            ft.addToBackStack(null)

            fragment.show(ft, fragment::class.java.simpleName)
        } catch (e: Exception) {

            Log.e("AlertDialogUtil", "showDialogFragment : ${e.printStackTrace()} ")

            val intent = Intent(activity, MainActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK.and(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            activity.startActivity(intent)
            activity.finish()


        }
    }

}