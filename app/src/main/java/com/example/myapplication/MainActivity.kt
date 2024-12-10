package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.PlayArrow
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.myapplication.compose.lazyCol.SimpleLazyList
import com.example.myapplication.compose.simpleNavi.Favorite
import com.example.myapplication.ui.theme.MyApplicationTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            MyApplicationTheme {
                SimpleLazyList(10, modifier = Modifier)
            }
        }

    }
}

sealed class BottomNavItem(val route: String, val name: String, val icon: ImageVector) {
    object PlayArrow : BottomNavItem("playArrow", "PlayArrow", Icons.Filled.PlayArrow)
    object Favorite : BottomNavItem("favorite", "Favorite", Icons.Filled.Favorite)

}

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true, device = "id:pixel_8_pro")
@Composable
fun Preview() {

    val topLevelRoutes = listOf(
        BottomNavItem.Favorite, BottomNavItem.PlayArrow
    )
    val navController = rememberNavController()

    var itemStatus by rememberSaveable { mutableStateOf(0) }
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior(rememberTopAppBarState())
    val scrollBehaviorPinned = TopAppBarDefaults.pinnedScrollBehavior()

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
                        scrollBehavior = scrollBehaviorPinned,
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
                composable(BottomNavItem.PlayArrow.route) { SimpleLazyList(10, Modifier) }
                composable(BottomNavItem.Favorite.route) { Favorite(Modifier) }
            }

        }
    }
}

