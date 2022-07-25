package com.allendevbang.weathertotravel.ui

import androidx.compose.animation.*
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
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
import com.allendevbang.weathertotravel.viewmodel.MainScreenViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.androidx.compose.getViewModel

@Composable
fun MainScreen(
    modifier: Modifier = Modifier, viewModel: MainScreenViewModel = getViewModel()
) {
    val normalWeatherUiState = viewModel._normalWeatherUiState
    val listState = rememberLazyGridState()
    val scope = rememberCoroutineScope()
    val isFABVisible = remember {
        derivedStateOf {
            listState.firstVisibleItemIndex > 4
        }
    }
    Box(modifier = modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        if (normalWeatherUiState.isLoading) {
            CircularProgressIndicator()
        }
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(8.dp),
            state = listState
        ) {
            val weatherList = normalWeatherUiState.data?.records?.location ?: mutableListOf()
            items(items = weatherList) { location ->
                Box(
                    modifier = Modifier
                        .heightIn(min = 100.dp)
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

                        }
                ) {
                    Text(
                        text = location?.locationName ?: "",
                        modifier = Modifier.align(Alignment.Center)
                    )
                }
            }
        }
        AnimatedVisibility(
            visible = isFABVisible.value,modifier = Modifier
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