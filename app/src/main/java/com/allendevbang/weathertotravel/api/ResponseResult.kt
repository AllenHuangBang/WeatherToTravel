package com.allendevbang.weathertotravel.api

sealed class ResponseResult<T>(val data: T? = null, val message: String? = "") {
    class Success<T>(data: T) : ResponseResult<T>(data)
    class Error<T>(message: String?, data: T? = null) : ResponseResult<T>(data, message)
}