package com.allendevbang.weathertotravel

import android.app.Application
import org.koin.core.context.startKoin

class WeatherToTravelApplication:Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            modules(weatherToTravelModule)
        }
    }
}