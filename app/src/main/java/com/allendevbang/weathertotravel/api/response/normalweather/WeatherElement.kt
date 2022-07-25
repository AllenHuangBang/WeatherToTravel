package com.allendevbang.weathertotravel.api.response.normalweather


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class WeatherElement(
    @SerialName("elementName")
    val elementName: String? = "",
    @SerialName("time")
    val time: List<Time?>? = listOf()
)