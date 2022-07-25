package com.allendevbang.weathertotravel

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.allendevbang.weathertotravel.nav.Routes
import com.allendevbang.weathertotravel.ui.WeatherBottomNav
import com.allendevbang.weathertotravel.ui.MainScreen
import com.allendevbang.weathertotravel.ui.SettingScreen
import com.allendevbang.weathertotravel.ui.theme.WeatherToTravelTheme
import com.allendevbang.weathertotravel.ui.topbar.WeatherToTravelTopBar
import com.allendevbang.weathertotravel.viewmodel.MainScreenViewModel
import org.koin.androidx.viewmodel.ext.android.getViewModelFactory

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navHostController = rememberNavController()
            WeatherToTravelTheme {
                // A surface container using the 'background' color from the theme
                Scaffold(
                    topBar = {
                        WeatherToTravelTopBar(){

                        }
                    },
                    bottomBar = {
                        WeatherBottomNav(navHostController = navHostController)
                    },
                ) { innerPadding ->
                    WeatherNavHost(
                        navHostController = navHostController,
                        innerPadding = innerPadding
                    )
                }
            }
        }
    }
}

@Composable
fun WeatherNavHost(navHostController: NavHostController, innerPadding: PaddingValues) {
    NavHost(
        navController = navHostController,
        startDestination = Routes.MainScreen,
        modifier = Modifier.padding(innerPadding)
    ) {
        composable(Routes.MainScreen) {
            MainScreen()
        }
        composable(Routes.SettingScreen) {
            SettingScreen()
        }
    }
}