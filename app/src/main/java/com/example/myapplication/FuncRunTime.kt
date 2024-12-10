package com.example.myapplication

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext
import kotlin.system.measureTimeMillis


fun testFunctionRunTIme(run: () -> Unit): Long {
    val timeMillis = measureTimeMillis {
        run()
    }

    return timeMillis
}

suspend fun run1000msFun() = withContext(Dispatchers.Main) {
    delay(1000)
}

