package com.allendevbang.weathertotravel.ui.botttom_nav

import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.allendevbang.weathertotravel.nav.BottomBarItems

@Composable
fun WeatherBottomNav(navHostController: NavHostController) {
    BottomNavigation {
        val backStackEntry by navHostController.currentBackStackEntryAsState()
        val currentRoute = backStackEntry?.destination?.route
        BottomBarItems.barItems.forEach { barItem ->
            BottomNavigationItem(
                selected = currentRoute == barItem.route,
                onClick = {
                    navHostController.navigate(barItem.route) {
                        popUpTo(navHostController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                icon = { Icon(imageVector = barItem.icon?:Icons.Default.Home, contentDescription = barItem.route) },
                label = { Text(text = stringResource(id = barItem.title)) })
        }
    }
}