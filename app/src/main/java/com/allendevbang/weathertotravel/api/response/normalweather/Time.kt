package com.allendevbang.weathertotravel.api.response.normalweather


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class Time(
    @SerialName("endTime")
    val endTime: String? = "",
    @SerialName("parameter")
    val parameter: Parameter? = Parameter(),
    @SerialName("startTime")
    val startTime: String? = ""
)