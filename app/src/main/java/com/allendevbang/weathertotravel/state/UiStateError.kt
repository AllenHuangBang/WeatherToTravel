package com.allendevbang.weathertotravel.state

import androidx.annotation.StringRes
import com.allendevbang.weathertotravel.R

sealed class UiStateError(@StringRes val stringRes: Int) {
    class NetworkError(@StringRes stringRes: Int = R.string.network_error) : UiStateError(stringRes)
}