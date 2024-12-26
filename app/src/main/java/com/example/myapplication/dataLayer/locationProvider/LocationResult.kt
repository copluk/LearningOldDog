package com.example.myapplication.dataLayer.locationProvider

import com.example.myapplication.dataLayer.model.LocationModel

data class LocationResult(
    override val lat: Long,
    override val long: Long,
    override val time: Int,
    override val ms: Int,
    override val header: Double,
    override val hight: Double,
) : LocationModel