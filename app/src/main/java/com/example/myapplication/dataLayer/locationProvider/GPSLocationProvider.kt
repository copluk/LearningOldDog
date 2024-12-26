package com.example.myapplication.dataLayer.locationProvider

import android.content.Context
import android.location.Location
import android.location.LocationManager

class GPSLocationProvider(private val context: Context) : ILocationProvider {

    private val locationManager = context.getSystemService(Context.LOCATION_SERVICE) as LocationManager
    private var locationCallback: LocationCallback? = null

    override fun requestLocation(callback: LocationCallback?) {

        locationCallback = callback

    }

    fun stop() {
        locationCallback = null
    }

    fun findLocation(){
        locationCallback?.onLocationResult(LocationResult(0L , 0L , 11 , 22 , 0.0 , 0.0))
    }

}