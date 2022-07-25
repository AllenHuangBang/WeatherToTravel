package com.allendevbang.weathertotravel.api.response.normalweather


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class NormalWeatherResponse(
    @SerialName("records")
    val records: Records? = Records(),
    @SerialName("result")
    val result: Result? = Result(),
    @SerialName("success")
    val success: String? = ""
)