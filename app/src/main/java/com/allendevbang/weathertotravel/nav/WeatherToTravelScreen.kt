package com.allendevbang.weathertotravel.nav

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.ui.graphics.vector.ImageVector
import com.allendevbang.weathertotravel.R

sealed class WeatherToTravelScreen(val route: String, @StringRes val title: Int, val icon: ImageVector?) {
    object MainScreen :
        WeatherToTravelScreen(
            Routes.MainScreen,
            R.string.main_screen_item_title,
            Icons.Filled.Home
        )

    object SettingScreen : WeatherToTravelScreen(
        Routes.SettingScreen,
        R.string.setting_screen_item_title,
        Icons.Filled.Settings
    )

    object DetailScreen : WeatherToTravelScreen(
        Routes.DetailScreen,
        R.string.empty,
        Icons.Filled.Home
    )

    object Routes {
        val MainScreen = "main_screen"
        val SettingScreen = "setting_screen"
        val DetailScreen = "detail_screen"
    }
}

object NavParameter{
    val Location = "/{location}"
}