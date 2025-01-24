package com.example.myapplication.dataLayer.locationProvider

import com.example.myapplication.dataLayer.model.LocationModel

interface LocationCallback {
    fun onLocationResult(locationResult: LocationModel)
}