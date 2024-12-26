package com.example.myapplication

import android.location.Location
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Button
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MediumTopAppBar
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.myapplication.compose.lazyCol.SimpleLazyList
import com.example.myapplication.compose.permission.NativePermission
import com.example.myapplication.compose.permission.onPermissionResult
import com.example.myapplication.compose.simpleNavi.Favorite
import com.example.myapplication.dataLayer.locationProvider.BLELocationProvider
import com.example.myapplication.dataLayer.locationProvider.LocationCallback
import com.example.myapplication.dataLayer.manager.LocationManager
import com.example.myapplication.dataLayer.locationProvider.LocationResult
import com.example.myapplication.ui.theme.MyApplicationTheme
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import com.google.accompanist.permissions.shouldShowRationale


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val locationManager: LocationManager = LocationManager()
        locationManager.setActiveProvider(BLELocationProvider())

        locationManager.requestLocation(object : LocationCallback {
            override fun onLocationResult(locationResult: LocationResult) {
                val location: Location? = locationResult.getLastLocation()
                // Use the location data
            }
        })


        setContent {
            MainView()
        }

    }


}


sealed class BottomNavItem(val route: String, val name: String, val icon: ImageVector) {
    object PlayArrow : BottomNavItem("playArrow", "PlayArrow", Icons.Filled.PlayArrow)
    object Favorite : BottomNavItem("favorite", "Favorite", Icons.Filled.Favorite)

}

@OptIn(ExperimentalPermissionsApi::class)
@Preview
@Composable
fun testCon() {
    MainView()
}

@Composable
fun AlertDialog(shouldShowDialog: Boolean, onAction: (Boolean) -> Unit) {

    AlertDialog(onDismissRequest = {
        onAction(false)
    },
        title = { Text(text = "Alert Dialog") },
        text = { Text(text = "Jetpack Compose Alert Dialog") },
        confirmButton = { // 6
            Button(
                onClick = {
                    onAction(false)
                }
            ) {
                Text(
                    text = "Confirm",
                    color = Color.White
                )
            }
        })
}

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun PermissionRequest() {

    val activity = LocalContext.current as MainActivity

    val accessFine =
        rememberPermissionState(android.Manifest.permission.ACCESS_FINE_LOCATION) { isGranted ->
            Log.i("DEBUG", "rememberPermissionState invoke ${isGranted}")

            if (isGranted) {
                // Permission granted


            } else {
                // Show rationale if needed

            }
        }



    LaunchedEffect(accessFine) {
        if (!accessFine.status.isGranted && accessFine.status.shouldShowRationale) {
            // Show rationale if needed
            Log.i("DEBUG", "permission !granted")

        } else {
            Log.i("DEBUG", "isGranted permission denied")

            accessFine.launchPermissionRequest()
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainView() {

    NativePermission(android.Manifest.permission.ACCESS_FINE_LOCATION, object : onPermissionResult{
        override fun onSuccess(isGranted: Boolean) {
        }

        override fun onReject(isGranted: Boolean) {
        }

        override fun onNoAgain(isGranted: Boolean) {
        }
    })

    val topLevelRoutes = listOf(
        BottomNavItem.Favorite, BottomNavItem.PlayArrow
    )
    val navController = rememberNavController()
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior(rememberTopAppBarState())

    MyApplicationTheme {
        Scaffold(
            modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
            topBar = {

                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentDestination = navBackStackEntry?.destination?.route

                if (currentDestination != BottomNavItem.PlayArrow.route) {
                    TopAppBar(
                        title = { Text(text = "MediumTopAppBar") },
                        navigationIcon = {
                            IconButton(onClick = {}) {
                                Icon(
                                    modifier = Modifier,
                                    imageVector = Icons.Filled.ArrowBack,
                                    contentDescription = "Localized description"
                                )
                            }
                        }
                    )
                } else {
                    MediumTopAppBar(
                        title = { Text(text = "MediumTopAppBar") },
                        scrollBehavior = scrollBehavior,
                        navigationIcon = {
                            IconButton(onClick = {}) {
                                Icon(
                                    modifier = Modifier,
                                    imageVector = Icons.Filled.ArrowBack,
                                    contentDescription = "Localized description"
                                )
                            }
                        }
                    )
                }

            },
            bottomBar = {
                BottomNavigation {
                    val navBackStackEntry by navController.currentBackStackEntryAsState()
                    val currentDestination = navBackStackEntry?.destination?.route

                    topLevelRoutes.forEach { bottomNavItem ->
                        BottomNavigationItem(
                            modifier = Modifier
                                .background(MaterialTheme.colorScheme.surfaceTint),
                            icon = {
                                Icon(
                                    modifier = Modifier,
                                    imageVector = bottomNavItem.icon,
                                    tint = MaterialTheme.colorScheme.inverseOnSurface,
                                    contentDescription = bottomNavItem.name
                                )
                            },
                            label = {
                                Text(
                                    bottomNavItem.name,
                                    color = MaterialTheme.colorScheme.inverseOnSurface
                                )
                            },
                            selected = currentDestination == bottomNavItem.name,
                            onClick = {
                                navController.navigate(bottomNavItem.name) {
                                    // Pop up to the start destination of the graph to
                                    // avoid building up a large stack of destinations
                                    // on the back stack as users select items
                                    popUpTo(navController.graph.findStartDestination().id) {
                                        saveState = true
                                    }
                                    // Avoid multiple copies of the same destination when
                                    // reselecting the same item
                                    launchSingleTop = true
                                    // Restore state when reselecting a previously selected item
                                    restoreState = true
                                }
                            }
                        )
                    }
                }

//                BottomBar(itemStatus, onItemChange = { i -> itemStatus = i })
            },

            ) { innerPadding ->

            NavHost(
                navController,
                startDestination = BottomNavItem.PlayArrow.route,
                Modifier.padding(innerPadding)
            ) {
                composable(BottomNavItem.PlayArrow.route) { SimpleLazyList(10, Modifier) {} }
                composable(BottomNavItem.Favorite.route) { Favorite(Modifier) }
            }

        }
    }
}

