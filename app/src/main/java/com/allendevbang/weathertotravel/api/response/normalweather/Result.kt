package com.allendevbang.weathertotravel.api.response.normalweather


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class Result(
    @SerialName("fields")
    val fields: List<Field?>? = listOf(),
    @SerialName("resource_id")
    val resourceId: String? = ""
)