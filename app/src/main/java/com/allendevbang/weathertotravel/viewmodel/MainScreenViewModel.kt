package com.allendevbang.weathertotravel.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.allendevbang.weathertotravel.api.ResponseResult
import com.allendevbang.weathertotravel.state.NormalWeatherUiState
import com.allendevbang.weathertotravel.usecase.WeatherUseCaseImpl
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class MainScreenViewModel(val weatherUseCase: WeatherUseCaseImpl) : ViewModel() {

    var _normalWeatherUiState by mutableStateOf(NormalWeatherUiState())
        private set

    init {
        getNormalWeather()
    }

    fun getNormalWeather() {
        weatherUseCase().onEach {
            _normalWeatherUiState = when (val data = it) {
                is ResponseResult.Success -> {
                    NormalWeatherUiState(data = data.data)
                }
                is ResponseResult.Error -> {
                    NormalWeatherUiState()
                }
                is ResponseResult.Loading -> {
                    NormalWeatherUiState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }
}