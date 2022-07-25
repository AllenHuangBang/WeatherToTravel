package com.allendevbang.weathertotravel.state

abstract class UiState(
    var isLoading: Boolean = false,
    var error: Error? = null
) {
    sealed class Error {
        object NetworkError : Error()
    }
}
