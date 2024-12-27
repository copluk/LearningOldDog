package com.example.myapplication.dataLayer.manager

import com.example.myapplication.dataLayer.locationProvider.ILocationProvider
import com.example.myapplication.dataLayer.locationProvider.LocationCallback

class LocationManager {

    private var activeProvider: ILocationProvider? = null

    fun setActiveProvider(provider: ILocationProvider?) {
        this.activeProvider = provider
    }

    fun requestLocation(callback: LocationCallback?) {
        if (activeProvider != null) {
            activeProvider?.initProvider(callback)
        } else {
            // Handle the case where no provider is available
        }
    }

    fun startRequest(){
        activeProvider?.startRequest()
    }

    fun stopRequest(){
        activeProvider?.stopRequest()
    }

    fun end(){
        activeProvider?.endProvider()
    }

}