package com.allendevbang.weathertotravel.api

import com.allendevbang.weathertotravel.state.UiStateError

sealed class ResponseResult<T>(val data: T? = null, val message: String? = "",val uiStateError: UiStateError? = null) {
    class Success<T>(data: T) : ResponseResult<T>(data)
    class Error<T>(uiStateError: UiStateError?, data: T? = null) : ResponseResult<T>(data, uiStateError = uiStateError)
    class Loading<T>:ResponseResult<T>()
}