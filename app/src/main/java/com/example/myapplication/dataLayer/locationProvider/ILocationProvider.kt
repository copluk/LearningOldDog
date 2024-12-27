package com.example.myapplication.dataLayer.locationProvider

interface ILocationProvider {

    fun initProvider(callback: LocationCallback?)
    fun startRequest()
    fun stopRequest()
    fun endProvider()

}