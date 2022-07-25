package com.allendevbang.weathertotravel.api.response.normalweather


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class Parameter(
    @SerialName("parameterName")
    val parameterName: String? = "",
    @SerialName("parameterUnit")
    val parameterUnit: String? = "",
    @SerialName("parameterValue")
    val parameterValue: String? = ""
)