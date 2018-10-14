package com.example.ashishpatel.myapplication

import android.annotation.SuppressLint
import android.location.Location
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.Button
import android.widget.TextView
import com.google.android.gms.common.ConnectionResult
import com.google.android.gms.common.GooglePlayServicesUtil
import com.google.android.gms.common.api.GoogleApiClient
import com.google.android.gms.location.LocationListener
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationServices
import java.text.DateFormat
import java.util.*


class MainActivity : AppCompatActivity(), LocationListener,
        GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener {
    private val TAG = "LocationActivity"
    private val INTERVAL = (1000 * 10).toLong()
    private val FASTEST_INTERVAL = (1000 * 5).toLong()
    var btnFusedLocation: Button? = null
    var tvLocation: TextView? = null
    lateinit var mLocationRequest: LocationRequest
    var mGoogleApiClient: GoogleApiClient? = null
    var mCurrentLocation: Location? = null
    var mLastUpdateTime: String? = null
    
    protected fun createLocationRequest() {
        mLocationRequest = LocationRequest()
        mLocationRequest.setInterval(INTERVAL)
        mLocationRequest.setFastestInterval(FASTEST_INTERVAL)
        mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("FragmentActivity.TAG", "onCreate ...............................")
        //show error dialog if GoolglePlayServices not available
        if (!isGooglePlayServicesAvailable()) {
            finish()
        }
        createLocationRequest()
        mGoogleApiClient = GoogleApiClient.Builder(this)
                .addApi(LocationServices.API)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .build()

        setContentView(R.layout.activity_main)
        tvLocation = findViewById(R.id.tvLocation) as TextView

        btnFusedLocation = findViewById(R.id.btnShowLocation) as Button
        btnFusedLocation!!.setOnClickListener{  updateUI()}

    }

    public override fun onStart() {
        super.onStart()
        Log.d("FragmentActivity.TAG", "onStart fired ..............")
        mGoogleApiClient!!.connect()
    }

    public override fun onStop() {
        super.onStop()
        Log.d("FragmentActivity.TAG", "onStop fired ..............")
        mGoogleApiClient!!.disconnect()
        Log.d("FragmentActivity.TAG", "isConnected ...............: " + mGoogleApiClient!!.isConnected())
    }

    private fun isGooglePlayServicesAvailable(): Boolean {
        val status = GooglePlayServicesUtil.isGooglePlayServicesAvailable(this)
        if (ConnectionResult.SUCCESS == status) {
            return true
        } else {
            GooglePlayServicesUtil.getErrorDialog(status, this, 0).show()
            return false
        }
    }

    override fun onConnected(bundle: Bundle?) {
        Log.d("FragmentActivity.TAG", "onConnected - isConnected ...............: " + mGoogleApiClient!!.isConnected())
        startLocationUpdates()
    }

    @SuppressLint("MissingPermission")
    protected fun startLocationUpdates() {
        val pendingResult = LocationServices.FusedLocationApi.requestLocationUpdates(
                mGoogleApiClient, mLocationRequest, this)
        Log.d("FragmentActivity.TAG", "Location update started ..............: ")
    }

    override fun onConnectionSuspended(i: Int) {

    }

    override fun onConnectionFailed(connectionResult: ConnectionResult) {
        Log.d("FragmentActivity.TAG", "Connection failed: " + connectionResult.toString())
    }

    override fun onLocationChanged(location: Location) {
        Log.d("FragmentActivity.TAG", "Firing onLocationChanged.............................$location")
        mCurrentLocation = location
        mLastUpdateTime = DateFormat.getTimeInstance().format(Date())
        updateUI()
    }

    private fun updateUI() {
        Log.d("FragmentActivity.TAG", "UI update initiated .............")
        if (null != mCurrentLocation) {
            val lat = (mCurrentLocation!!.getLatitude()).toString()
            val lng =(mCurrentLocation!!.getLongitude()).toString()
            tvLocation!!.setText("At Time: " + mLastUpdateTime + "\n" +
                    "Latitude: " + lat + "\n" +
                    "Longitude: " + lng + "\n" +
                    "Accuracy: " + mCurrentLocation!!.getAccuracy() + "\n" +
                    "Provider: " + mCurrentLocation!!.getProvider())
        } else {
            Log.d("FragmentActivity.TAG", "location is null ...............")
        }
    }

    override fun onPause() {
        super.onPause()
        stopLocationUpdates()
    }

    protected fun stopLocationUpdates() {
        LocationServices.FusedLocationApi.removeLocationUpdates(
                mGoogleApiClient, this)
        Log.d("FragmentActivity.TAG", "Location update stopped .......................")
    }

    public override fun onResume() {
        super.onResume()
        if (mGoogleApiClient!!.isConnected()) {
            startLocationUpdates()
            Log.d("FragmentActivity.TAG", "Location update resumed .....................")
        }
    }

}
