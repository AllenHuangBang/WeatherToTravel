package com.allendevbang.weathertotravel.state

import com.allendevbang.weathertotravel.api.response.normalweather.NormalWeatherResponse

data class NormalWeatherUiState(
    val data: NormalWeatherResponse? = null,
    var isLoading: Boolean = false,
    var error: UiStateError? = null
)