package com.example.myapplication.compose.permission

import android.Manifest
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import com.example.myapplication.AlertDialog

interface onPermissionResult {
    fun onSuccess(isGranted: Boolean)
    fun onReject(isGranted: Boolean)
    fun onNoAgain(isGranted: Boolean)
}

@Composable
fun nativePermission(permission: String, onPermissionResult: onPermissionResult) {
    val shouldShowDialog = remember { mutableStateOf(false) }

    val startPermissionRequest = System.currentTimeMillis()
    var endPermissionRequest = 0L
    val microphonePermissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission(),
        onResult = { isGranted ->
            Log.i("DEBUG", "nativePermission invoke ${isGranted}")
            endPermissionRequest = System.currentTimeMillis()

            if (!isGranted && endPermissionRequest - startPermissionRequest > 400L) {
                Log.i("DEBUG", "nativePermission invoke")
                shouldShowDialog.value = true
                onPermissionResult.onNoAgain(isGranted)
            } else if(!isGranted){
                Log.i("DEBUG", "nativePermission invoke !isGranted")
                onPermissionResult.onReject(isGranted)
            }
            else {
                onPermissionResult.onSuccess(isGranted)
            }
        }
    )

    LaunchedEffect(microphonePermissionLauncher) {
        microphonePermissionLauncher.launch(permission)
    }

    if (shouldShowDialog.value) {
        AlertDialog(shouldShowDialog.value) { b ->
            shouldShowDialog.value = b
        }
    }

}