package com.allendevbang.weathertotravel.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.allendevbang.weathertotravel.api.response.normalweather.Location
import com.google.gson.Gson

@Composable
fun DetailScreen(locationString: String?, titleChange: (String) -> Unit) {
    val location = Gson().fromJson(locationString, Location::class.java)
    titleChange(location.locationName ?: "")
    LazyColumn(
        contentPadding = PaddingValues(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(
            items = location.weatherElement?.find { it?.elementName == "Wx" }?.time ?: listOf()
        ) { time ->
            Column {
                Text(text = "${time?.startTime} to ${time?.endTime}", modifier = Modifier.align(Alignment.CenterHorizontally))
                WeatherNormalBox(clickable = null, modifier = Modifier.fillMaxWidth()) {
                    Text(
                        text = time?.parameter?.parameterName ?: "",
                        modifier = Modifier.align(Alignment.Center)
                    )
                } 
            }
        }
    }
}