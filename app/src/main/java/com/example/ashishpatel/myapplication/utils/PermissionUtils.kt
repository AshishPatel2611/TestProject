package com.example.ashishpatel.myapplication.utils

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.provider.Settings
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.support.v7.app.AlertDialog
import com.example.ashishpatel.myapplication.R

/**
 * Created by ashish on 8/9/17.
 */

object PermissionUtils {

    const val STORAGE_PERMISSION = Manifest.permission.READ_EXTERNAL_STORAGE
    const val CAMERA_PERMISSION = Manifest.permission.CAMERA
    const val CALENDER_PERMISSION = Manifest.permission.READ_CALENDAR
    const val CONTACTS_PERMISSION = Manifest.permission.READ_CONTACTS
    const val LOCATION_PERMISSION = Manifest.permission.ACCESS_FINE_LOCATION
    const val MICROPHONE_PERMISSION = Manifest.permission.RECORD_AUDIO
    const val SMS_PERMISSION = Manifest.permission.SEND_SMS
    const val PHONE_PERMISSION = Manifest.permission.CALL_PHONE

    interface OnPermissionRequestedResult {

        fun permissionGranted()

        fun permissionDenied()

        fun permissionSetNeverAskAgain()
    }

    fun requestPermission(activity: Activity, requestCode: Int, manifestPermissionName: String): Boolean {

        // call this method , where required

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(activity, manifestPermissionName) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(activity, arrayOf(manifestPermissionName), requestCode)
                return false
            } else {
                return true
            }
        } else {
            return true
        }
    }

    fun checkPermissionResult(activity: Activity, manifestPermissionName: String, onPermissionRequestedResult: OnPermissionRequestedResult) {

        // call this method in activity result with Manifest permission name

        if (ContextCompat.checkSelfPermission(activity, manifestPermissionName) != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(activity, manifestPermissionName)) {
                onPermissionRequestedResult.permissionDenied()
            } else {
                onPermissionRequestedResult.permissionSetNeverAskAgain()
            }
        } else {
            onPermissionRequestedResult.permissionGranted()
        }
    }


    fun showPermissionRequiredReasonDialog(activity: Activity, permissionName: String, manifestPermissionName: String,
                                           requiredFor: String, requestCode: Int) {

        // show this dialog if user has denied permission before

        AlertDialog.Builder(activity, R.style.AppTheme)
                .setTitle(permissionName)
                .setMessage("$permissionName Permission required for $requiredFor")
                .setPositiveButton("Ok") { dialogInterface, i ->
                    //Prompt the user once explanation has been shown
                    ActivityCompat.requestPermissions(activity,
                            arrayOf(manifestPermissionName),
                            requestCode)
                }
                .setCancelable(false)
                .create()
                .show()
    }


    fun showPermissionEnableFromSettingDialog(activity: Activity, permissionName: String, requiredFor: String, requestCode: Int) {

        // show this dialog if user has denied permission before
        // and set never ask again checked...
        // result will caught in onActivityResults....

        AlertDialog.Builder(activity, R.style.AppTheme)
                .setTitle(permissionName)
                .setMessage("$permissionName Permission required for $requiredFor!")
                .setPositiveButton("Go To Settings") { dialogInterface, i ->
                    val intent = Intent()
                    intent.action = Settings.ACTION_APPLICATION_DETAILS_SETTINGS
                    val uri = Uri.fromParts("package", activity.getPackageName(), null)
                    intent.data = uri
                    activity.startActivityForResult(intent, requestCode)
                }
                .setCancelable(false)
                .create()
                .show()
    }

}
