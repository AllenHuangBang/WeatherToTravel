package com.allendevbang.weathertotravel.nav

sealed class BottomNavRoutes(val route:String) {
    object MainScreen:BottomNavRoutes(Routes.MainScreen)
}
object Routes{
    const val MainScreen = "main_screen"
    const val SettingScreen = "setting_screen"
}