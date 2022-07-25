package com.allendevbang.weathertotravel.api

import com.allendevbang.weathertotravel.api.response.normalweather.NormalWeatherResponse

interface WeatherRepo {
    suspend fun getNormalWeather():ResponseResult<NormalWeatherResponse>
}