package com.allendevbang.weathertotravel.state

sealed class UiStateError {
    object NetworkError : UiStateError()
}