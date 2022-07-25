package com.allendevbang.weathertotravel.api.response.normalweather


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class Location(
    @SerialName("locationName")
    val locationName: String? = "",
    @SerialName("weatherElement")
    val weatherElement: List<WeatherElement?>? = listOf()
)