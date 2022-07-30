package com.allendevbang.weathertotravel.ui.topbar

import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.allendevbang.weathertotravel.R

@Composable
fun WeatherToTravelTopBar(title: String = stringResource(id = R.string.app_name),onMenuClicked:()->Unit) {
    TopAppBar(title = {
        Text(text = title)
    }, navigationIcon = {
        IconButton(onClick = onMenuClicked) {
            Icon(imageVector = Icons.Filled.Menu, contentDescription = "menu")
        }
    })
}