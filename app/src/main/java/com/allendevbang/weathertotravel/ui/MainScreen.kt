package com.allendevbang.weathertotravel.ui

import android.widget.Toast
import androidx.compose.animation.*
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.allendevbang.weathertotravel.api.response.normalweather.Location
import com.allendevbang.weathertotravel.nav.WeatherToTravelScreen
import com.allendevbang.weathertotravel.state.UiStateError
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
    val context = LocalContext.current
    val isFABVisible = remember {
        derivedStateOf {
            listState.firstVisibleItemIndex > 2
        }
    }
    Box(modifier = modifier, contentAlignment = Alignment.Center) {
        if (normalWeatherUiState.isLoading) {
            CircularProgressIndicator()
        }
        normalWeatherUiState.error?.let { uiStateError ->
            when (uiStateError) {
                is UiStateError.NetworkError -> {
                    Toast.makeText(context, uiStateError.stringRes, Toast.LENGTH_SHORT).show()
                }
            }
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
                            WeatherNormalBox(modifier = Modifier
                                .fillMaxWidth(),
                                clickable = {
                                    val locationString = Gson().toJson(location)
                                    navHostController.navigate(WeatherToTravelScreen.DetailScreen.route + "/$locationString")
                                }) {
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
