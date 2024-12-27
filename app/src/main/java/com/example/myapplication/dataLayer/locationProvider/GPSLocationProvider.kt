package com.example.myapplication.dataLayer.locationProvider

import android.content.Context
import android.location.LocationManager
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.cancel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class GPSLocationProvider(private val context: Context) : ILocationProvider {

    private val locationManager =
        context.getSystemService(Context.LOCATION_SERVICE) as LocationManager
    private var locationCallback: LocationCallback? = null

    private val loopScope = MainScope()
    override fun initProvider(callback: LocationCallback?) {

        locationCallback = callback
    }

    override fun startRequest() {
        loopScope.launch {
            loopLocation()
        }
    }

    override fun stopRequest() {
        loopScope.cancel()
    }

    override fun endProvider() {
        stop()
    }

    private suspend fun loopLocation() {
        findLocation()
        delay(100)
        loopLocation()
    }


    private fun stop() {
        locationCallback = null
    }

    private fun findLocation() {
        val ss: Int = (System.currentTimeMillis() / 1000).toInt()
        locationCallback?.onLocationResult(LocationResult(0L, 0L, ss, 22, 0.0, 0.0))
    }

}