package com.example.myapplication.dataLayer.model

interface LocationModel {
    val lat : Long
    val long : Long
    val time : Int
    val ms : Int
    val header : Double
    val hight : Double
}