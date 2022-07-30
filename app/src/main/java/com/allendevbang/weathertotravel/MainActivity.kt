package com.allendevbang.weathertotravel

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.allendevbang.weathertotravel.nav.NavParameter
import com.allendevbang.weathertotravel.nav.WeatherToTravelScreen
import com.allendevbang.weathertotravel.ui.DetailScreen
import com.allendevbang.weathertotravel.ui.WeatherBottomNav
import com.allendevbang.weathertotravel.ui.MainScreen
import com.allendevbang.weathertotravel.ui.SettingScreen
import com.allendevbang.weathertotravel.ui.theme.WeatherToTravelTheme
import com.allendevbang.weathertotravel.ui.topbar.WeatherToTravelTopBar
import com.allendevbang.weathertotravel.util.getNavParameterKey
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navHostController = rememberNavController()
            val scope = rememberCoroutineScope()
            val topAppBarTitle = rememberSaveable() {
                mutableStateOf(getString(R.string.app_name))
            }
            WeatherToTravelTheme {
                Scaffold(
                    topBar = {
                        WeatherToTravelTopBar(title = topAppBarTitle.value) {

                        }
                    },
                    bottomBar = {
                        WeatherBottomNav(navHostController = navHostController)
                    },
                ) { innerPadding ->
                    WeatherNavHost(
                        navHostController = navHostController,
                        innerPadding = innerPadding
                    ) {
                        topAppBarTitle.value = it
                    }
                    scope.launch {
                        navHostController.currentBackStackEntryFlow.collect {
                            if (it.destination.route == WeatherToTravelScreen.MainScreen.route) {
                                topAppBarTitle.value = getString(R.string.app_name)
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun WeatherNavHost(
    navHostController: NavHostController,
    innerPadding: PaddingValues,
    titleChange: (String) -> Unit
) {
    NavHost(
        navController = navHostController,
        startDestination = WeatherToTravelScreen.MainScreen.route,
        modifier = Modifier.padding(innerPadding)
    ) {
        composable(WeatherToTravelScreen.MainScreen.route) {
            MainScreen(modifier = Modifier.fillMaxSize(), navHostController = navHostController)
        }
        composable(WeatherToTravelScreen.SettingScreen.route) {
            SettingScreen()
        }
        composable(WeatherToTravelScreen.DetailScreen.route + NavParameter.Location) {
            DetailScreen(
                it.arguments?.getString(NavParameter.Location.getNavParameterKey()),
                titleChange
            )
        }
    }
}

