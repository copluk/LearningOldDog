package com.example.myapplication

import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.IBinder
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.myapplication.dataLayer.locationProvider.GPSLocationProvider
import com.example.myapplication.dataLayer.locationProvider.LocationCallback
import com.example.myapplication.dataLayer.manager.LocationManager
import com.example.myapplication.dataLayer.model.LocationModel

class LocationService : Service() {

    private lateinit var locationManager: LocationManager

    private val _locationLiveData = MutableLiveData<LocationModel>()
    val locationLiveData: LiveData<LocationModel> get() = _locationLiveData


    override fun onCreate() {
        super.onCreate()
        locationManager = LocationManager()
        locationManager.setActiveProvider(GPSLocationProvider(applicationContext))

        locationManager.requestLocation(object : LocationCallback {
            override fun onLocationResult(locationResult: LocationModel) {
                _locationLiveData.postValue(locationResult)
            }
        })
        locationManager.startRequest()



    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        return START_STICKY
    }

    override fun onDestroy() {
        super.onDestroy()

        locationManager.end()
    }

    inner class LocalBinder : Binder() {
        fun getService(): LocationService {
            return this@LocationService
        }
    }

    private val binder = LocalBinder()

    override fun onBind(intent: Intent?): IBinder {
        return binder
    }


}