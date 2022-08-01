package com.allendevbang.weathertotravel.usecase

import com.allendevbang.weathertotravel.api.ResponseResult
import com.allendevbang.weathertotravel.api.WeatherRepo
import com.allendevbang.weathertotravel.api.response.normalweather.NormalWeatherResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class WeatherUseCaseImpl(val weatherRepo: WeatherRepo) :
    UseCase<Any, Flow<ResponseResult<NormalWeatherResponse>>> {
    override fun invoke(input: Any?): Flow<ResponseResult<NormalWeatherResponse>> = flow {
        emit(ResponseResult.Loading())
        val normalWeatherResponse = weatherRepo.getNormalWeather()
        emit(normalWeatherResponse)
    }
}