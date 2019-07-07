package com.softhink.single.ui.dashboard

import android.annotation.SuppressLint
import android.content.Context
import android.location.Location
import androidx.lifecycle.LiveData
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationCallback

class CurrentLocationListener : LiveData<Location> {

    private lateinit var instance: CurrentLocationListener
    private var mFusedLocationClient: FusedLocationProviderClient
    private lateinit var locationRequest: LocationRequest

    @SuppressLint("MissingPermission")
    constructor(context: Context) : super() {
        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(context)
        mFusedLocationClient.lastLocation.addOnSuccessListener{
            if (it != null)
                value = it
        }
        createLocationRequest()
    }

    fun getInstance(appContext: Context): CurrentLocationListener {
        if (instance == null) {
            instance = CurrentLocationListener(appContext)
        }
        return instance
    }

    private fun createLocationRequest() {
        locationRequest = LocationRequest()
        locationRequest.interval = 10000
        locationRequest.fastestInterval = 5000
        locationRequest.priority = LocationRequest.PRIORITY_HIGH_ACCURACY
    }

    @SuppressLint("MissingPermission")
    override fun onActive() {
        super.onActive()
        mFusedLocationClient.requestLocationUpdates(locationRequest, mLocationCallback, null)
    }

    var mLocationCallback: LocationCallback = object : LocationCallback() {
        override fun onLocationResult(locationResult: LocationResult) {
            for (location in locationResult.locations) {
                if (location != null)
                    value = location
            }
        }
    }

    override fun onInactive() {
        super.onInactive()
        if (mLocationCallback != null)
            mFusedLocationClient.removeLocationUpdates(mLocationCallback)
    }
}