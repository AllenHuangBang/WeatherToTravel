package com.allendevbang.weathertotravel.usecase

import com.allendevbang.weathertotravel.api.ResponseResult
import com.allendevbang.weathertotravel.api.WeatherRepo
import com.allendevbang.weathertotravel.api.response.normalweather.NormalWeatherResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow

class WeatherUseCase(private val weatherRepo: WeatherRepo) :
    UseCase<Unit, Flow<ResponseResult<NormalWeatherResponse>>> {
    override fun invoke(input: Unit?): Flow<ResponseResult<NormalWeatherResponse>> = flow {
        emit(ResponseResult.Loading())
        val normalWeatherResponse = weatherRepo.getNormalWeather()
        emit(normalWeatherResponse)
    }
}