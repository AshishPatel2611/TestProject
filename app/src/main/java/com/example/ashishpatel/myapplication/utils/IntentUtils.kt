package com.example.ashishpatel.myapplication.utils

import android.Manifest
import android.app.Activity
import android.content.ActivityNotFoundException
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.support.v4.content.ContextCompat
import android.support.v4.content.ContextCompat.startActivity
import android.util.Log
import com.example.ashishpatel.myapplication.R
import java.net.URI
import java.net.URLEncoder


/**
 * Created by ashish on 10/3/18.
 */
object IntentUtils {

    fun callIntent(activity: Activity, phoneNumber: String) {

        if (ContextCompat.checkSelfPermission(activity, Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED) {
            val intent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:$phoneNumber"))
            startActivity(activity, intent, null)
        }
    }

    fun mapIntent(activity: Activity, address: String) {
        val mapIntent = Intent(Intent.ACTION_VIEW,
                Uri.parse(String.format("geo:0,0?q=%s",
                        URLEncoder.encode(address))))
        mapIntent.`package` = "com.ic_google_long.android.apps.maps"
        startActivity(activity, mapIntent, null)
    }

    fun shareIntent(activity: Activity, text: String) {
        val sendIntent = Intent()
        sendIntent.action = Intent.ACTION_SEND
        sendIntent.putExtra(Intent.EXTRA_TEXT, text)
        sendIntent.type = "text/plain"
        startActivity(activity, Intent.createChooser(sendIntent, "share to"), null)
    }


    fun shareContent(activity: Activity, uri: URI) {
        val shareIntent = Intent()
        shareIntent.action = Intent.ACTION_SEND
        shareIntent.putExtra(Intent.EXTRA_STREAM, uri)
        shareIntent.type = "image/jpeg"
        startActivity(activity, Intent.createChooser(shareIntent, "share to"), null)
    }


    fun shareMultipleContent(activity: Activity, uri: ArrayList<Uri>) {
        val imageUris = ArrayList<Uri>()
        imageUris.addAll(uri) // Add your image URIs here

        val shareIntent = Intent()
        shareIntent.action = Intent.ACTION_SEND_MULTIPLE
        shareIntent.putParcelableArrayListExtra(Intent.EXTRA_STREAM, imageUris)
        shareIntent.type = "image/*"
        startActivity(activity, Intent.createChooser(shareIntent, "Share images to.."), null)
    }

    fun URL_BrowserIntent(activity: Activity, url: String) {
        try {
            val i = Intent(Intent.ACTION_VIEW)
            if (!url.contains("http://")) {
                val newURL = ("http://").plus(url)
                i.data = Uri.parse(newURL)
            } else {
                i.data = Uri.parse(url)
            }

            activity.startActivity(i)
        } catch (e: ActivityNotFoundException) {
            Log.e("IntentUtils", "URL_BrowserIntent : ${e.printStackTrace()} ")
        }

    }
}