package com.allendevbang.weathertotravel.api.response.normalweather


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class Records(
    @SerialName("datasetDescription")
    val datasetDescription: String? = "",
    @SerialName("location")
    val location: List<Location?>? = listOf()
)