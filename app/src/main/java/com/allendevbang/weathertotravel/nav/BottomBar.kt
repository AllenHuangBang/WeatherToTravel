package com.allendevbang.weathertotravel.nav

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.ui.graphics.vector.ImageVector
import com.allendevbang.weathertotravel.R

data class BarItem(
    val title:Int,
    val icon:ImageVector,
    val route:String
)

object BottomBarItems{
    val barItems = listOf(
        BarItem(
            title = R.string.main_screen_item_title,
            icon = Icons.Filled.Home,
            route = Routes.MainScreen
        ),
        BarItem(
            title = R.string.setting_screen_item_title,
            icon = Icons.Filled.Settings,
            route = Routes.SettingScreen
        )
    )
}