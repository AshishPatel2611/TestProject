package com.example.ashishpatel.myapplication.utils

import android.app.Activity
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.net.ConnectivityManager
import java.util.concurrent.CopyOnWriteArraySet

class NetworkConnectionChecker(context: Activity) {

    private val connectivityManager: ConnectivityManager

    private val listeners = CopyOnWriteArraySet<OnConnectivityChangedListener>()

    init {
        this.connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val intentFilter = IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION)
        context.registerReceiver(NetworkStateReceiver(), intentFilter)
    }

    fun registerListener(listener: OnConnectivityChangedListener, activity: Activity) {
        listeners.add(listener)
        listener.connectivityChanged(isDataConnectionAvailable(activity))
    }

    fun unregisterListener(listener: OnConnectivityChangedListener) {
        listeners.remove(listener)
    }


    interface OnConnectivityChangedListener {
        fun connectivityChanged(availableNow: Boolean)
    }

    private inner class NetworkStateReceiver : BroadcastReceiver() {

        override fun onReceive(context: Context, intent: Intent) {
            val isConnectedNow = isDataConnectionAvailable(context)

            for (listener in listeners) {
                listener.connectivityChanged(isConnectedNow)
            }
        }
    }

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
