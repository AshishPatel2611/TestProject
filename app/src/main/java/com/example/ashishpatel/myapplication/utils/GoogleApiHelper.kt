package com.example.ashishpatel.myapplication.utils

import android.app.Activity
import android.os.Bundle
import android.util.Log


/**
 * Created by ashish on 11/9/17.
 */

/*class GoogleApiHelper(internal var context: Activity, internal var googleAPIclientConnected: GoogleAPIclientConnected) : GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {
    var googleApiClient: GoogleApiClient? = null
        internal set

    val isConnected: Boolean
        get() = if (googleApiClient != null) {
            googleApiClient!!.isConnected
        } else {
            false
        }

    private val isGooglePlayServicesAvailable: Boolean
        get() {
            val status = GooglePlayServicesUtil.isGooglePlayServicesAvailable(context)
            if (ConnectionResult.SUCCESS == status) {
                return true
            } else {
                GooglePlayServicesUtil.getErrorDialog(status, context, 0).show()
                return false
            }
        }

    init {
        buildGoogleApiClient()
        connect()
    }

    fun connect() {
        if (googleApiClient != null) {
            googleApiClient!!.connect()
        }
    }

    fun disconnect() {
        if (googleApiClient != null && googleApiClient!!.isConnected) {
            googleApiClient!!.disconnect()
        }
    }

    private fun buildGoogleApiClient() {

        if (isGooglePlayServicesAvailable) {
            googleApiClient = GoogleApiClient.Builder(context)
                    .addConnectionCallbacks(this)
                    .addOnConnectionFailedListener(this)
                    .addApi(LocationServices.API).build()
        }
        Log.i(TAG, "mGoogleApiClient.isConnected(): " + googleApiClient!!.isConnected)
    }

    override fun onConnected(bundle: Bundle?) {
        //You are connected do what ever you want
        //Like i get last known location
        googleAPIclientConnected.isGoogleAPIclientConnected(isConnected)
    }

    override fun onConnectionSuspended(i: Int) {
        Log.d(TAG, "onConnectionSuspended: googleApiClient.connect()")
        googleApiClient!!.connect()
    }

    override fun onConnectionFailed(connectionResult: ConnectionResult) {
        Log.d(TAG, "onConnectionFailed: connectionResult.toString() = " + connectionResult.toString())
    }

    interface GoogleAPIclientConnected {
        fun isGoogleAPIclientConnected(isConnected: Boolean?)
    }

    companion object {
        private val TAG = "GoogleApiHelper"
    }
}*/
