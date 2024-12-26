package com.example.myapplication.dataLayer.locationProvider

interface ILocationProvider {
    fun requestLocation(callback: LocationCallback?)
}