package com.allendevbang.weathertotravel.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.allendevbang.weathertotravel.nav.WeatherToTravelScreen
import com.google.gson.Gson

@Composable
fun WeatherNormalBox(
    modifier: Modifier = Modifier,
    clickable: (() -> Unit)?,
    content: @Composable BoxScope.() -> Unit
) {
    Box(
        modifier = modifier
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
            .clickable(enabled = clickable != null, onClick = clickable ?: {})
            .padding(8.dp),
        content = content
    )
}