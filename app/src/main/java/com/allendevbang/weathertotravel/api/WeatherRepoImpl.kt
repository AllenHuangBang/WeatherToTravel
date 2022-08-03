package com.allendevbang.weathertotravel.api

import com.allendevbang.weathertotravel.BuildConfig
import com.allendevbang.weathertotravel.R
import com.allendevbang.weathertotravel.api.response.normalweather.NormalWeatherResponse
import com.allendevbang.weathertotravel.state.UiStateError
import com.allendevbang.weathertotravel.util.Constants
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.http.*

class WeatherRepoImpl(val client: HttpClient) : WeatherRepo {
    override suspend fun getNormalWeather(): ResponseResult<NormalWeatherResponse> {
        val responseResult = try {
            ResponseResult.Success<NormalWeatherResponse>(
                client.get {
                    url {
                        protocol = URLProtocol.HTTPS
                        host = BuildConfig.API_HOST
                        path(Constants.API.Path.NormalWeatherPath)
                        parameters.append(Constants.API.AUTHORIZATION, Constants.API.API_KEY)
                    }
                }.body()
            )
        } catch (e: Exception) {
            ResponseResult.Error(
                UiStateError.NetworkError(R.string.network_error)
            )
        }
        return responseResult
    }

}