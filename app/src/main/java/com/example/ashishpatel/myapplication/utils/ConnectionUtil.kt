package com.example.ashishpatel.myapplication.utils

import android.content.Context
import android.net.ConnectivityManager

object ConnectionUtil {
    fun isDataConnectionAvailable(context: Context): Boolean {
        try {
            val conMgr = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            return conMgr.activeNetworkInfo != null && conMgr.activeNetworkInfo.isAvailable && conMgr.activeNetworkInfo.isConnected
        } catch (e: Exception) {
            e.printStackTrace()
            return false
        }
    }
}