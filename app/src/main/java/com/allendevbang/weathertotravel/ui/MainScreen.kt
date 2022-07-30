package com.allendevbang.weathertotravel.ui

import androidx.compose.animation.*
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.allendevbang.weathertotravel.api.response.normalweather.Location
import com.allendevbang.weathertotravel.nav.WeatherToTravelScreen
import com.allendevbang.weathertotravel.viewmodel.MainScreenViewModel
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.androidx.compose.getViewModel

@Composable
fun MainScreen(
    modifier: Modifier = Modifier,
    viewModel: MainScreenViewModel = getViewModel(),
    navHostController: NavHostController
) {
    val normalWeatherUiState = viewModel._normalWeatherUiState
    val listState = rememberLazyListState()
    val scope = rememberCoroutineScope()
    val isFABVisible = remember {
        derivedStateOf {
            listState.firstVisibleItemIndex > 2
        }
    }
    Box(modifier = modifier, contentAlignment = Alignment.Center) {
        if (normalWeatherUiState.isLoading) {
            CircularProgressIndicator()
        }

        val weatherList = normalWeatherUiState.data?.records?.location?.sortedBy { location ->
            findTheWeatherScore(location = location)
        } ?: mutableListOf()
        val weatherGroup = weatherList.groupBy { location ->
            findTheWeatherScore(location = location)
        }

        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            contentPadding = PaddingValues(vertical = 8.dp),
            state = listState
        ) {
            items(items = weatherGroup.toList()) { weatherGroup ->
                Column() {
                    LazyRow(
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        contentPadding = PaddingValues(horizontal = 8.dp)
                    ) {
                        items(items = weatherGroup.second) { location ->
                            Box(
                                modifier = Modifier
                                    .sizeIn(minHeight = 100.dp, minWidth = 100.dp)
                                    .shadow(8.dp, shape = RoundedCornerShape(16.dp))
                                    .clip(RoundedCornerShape(8.dp))
                                    .background(
                                        brush = Brush.linearGradient(
                                            colors = listOf(
                                                MaterialTheme.colors.primary,
                                                Color.White,
                                                Color.White,
                                                Color.White
                                            )
                                        )
                                    )
                                    .fillMaxWidth()
                                    .clickable {
                                        val locationString = Gson().toJson(location)
                                        navHostController.navigate(WeatherToTravelScreen.DetailScreen.route+"/$locationString",)
                                    }
                            ) {
                                Column(modifier = Modifier.align(Alignment.Center)) {
                                    Text(text = location?.locationName ?: "")
                                }
                            }
                        }
                    }
                }
            }
        }
        AnimatedVisibility(
            visible = isFABVisible.value, modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(12.dp), enter = slideInVertically(), exit = slideOutVertically()
        ) {
            FloatingActionButton(
                onClick = {
                    scope.launch(Dispatchers.Main) {
                        listState.animateScrollToItem(0)
                    }
                }, backgroundColor = MaterialTheme.colors.primary
            ) {
                Icon(
                    imageVector = Icons.Filled.ArrowBack,
                    modifier = Modifier.rotate(90f),
                    contentDescription = "up"
                )
            }
        }
    }
}

fun findTheWeatherScore(location: Location?): Int? =
    location?.weatherElement?.find { weatherElement ->
        weatherElement?.elementName == "Wx"
    }?.time?.map { time ->
        time?.parameter?.parameterValue?.toInt() ?: 0
    }?.sumOf {
        it
    }
