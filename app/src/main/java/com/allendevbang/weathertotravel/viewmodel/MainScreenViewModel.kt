package com.allendevbang.weathertotravel.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.allendevbang.weathertotravel.api.ResponseResult
import com.allendevbang.weathertotravel.api.WeatherRepo
import com.allendevbang.weathertotravel.state.NormalWeatherUiState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainScreenViewModel(val weatherRepo: WeatherRepo) : ViewModel() {

    var _normalWeatherUiState by mutableStateOf(NormalWeatherUiState())
        private set

    init {
        getNormalWeather()
    }

    fun getNormalWeather() {
        _normalWeatherUiState.isLoading = true
        viewModelScope.launch(Dispatchers.IO) {
            _normalWeatherUiState = when (val data = weatherRepo.getNormalWeather()) {
                is ResponseResult.Success -> {
                    _normalWeatherUiState.copy(data = data.data)
                }
                is ResponseResult.Error -> {
                    _normalWeatherUiState.copy(data = null)
                }
            }
        }
    }
}