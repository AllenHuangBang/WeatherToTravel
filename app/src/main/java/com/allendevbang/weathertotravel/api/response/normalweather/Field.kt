package com.allendevbang.weathertotravel.api.response.normalweather


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class Field(
    @SerialName("id")
    val id: String? = "",
    @SerialName("type")
    val type: String? = ""
)