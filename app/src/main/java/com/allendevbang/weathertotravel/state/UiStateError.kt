package com.allendevbang.weathertotravel.state

import androidx.annotation.StringRes

sealed class UiStateError(@StringRes val stringRes: Int) {
    class NetworkError(@StringRes stringRes: Int) : UiStateError(stringRes)
}