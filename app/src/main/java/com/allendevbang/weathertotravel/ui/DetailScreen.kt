package com.allendevbang.weathertotravel.ui

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import com.allendevbang.weathertotravel.api.response.normalweather.Location
import com.allendevbang.weathertotravel.api.response.normalweather.NormalWeatherResponse
import com.google.gson.Gson

@Composable
fun DetailScreen(locationString: String?,titleChange:(String)->Unit) {
    val location = Gson().fromJson(locationString,Location::class.java)
    titleChange(location.locationName?:"")
    LazyColumn(){
        items(items = location.weatherElement?.find { it?.elementName=="Wx" }?.time?: listOf()){ time->
            Text(text = time?.parameter?.parameterName?:"")
        }
    }
}