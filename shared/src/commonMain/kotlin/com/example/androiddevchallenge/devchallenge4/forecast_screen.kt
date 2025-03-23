/*
 * Copyright 2021 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.androiddevchallenge.devchallenge4

import androidx.compose.animation.core.withInfiniteAnimationFrameMillis
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.withFrameMillis
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.androiddevchallenge.MyTheme
import com.example.androiddevchallenge.shared.generated.resources.*
import com.example.androiddevchallenge.shared.generated.resources.Res
import com.example.androiddevchallenge.shared.generated.resources.clear
import com.example.androiddevchallenge.shared.generated.resources.cloudy_1
import com.example.androiddevchallenge.shared.generated.resources.cloudy_2
import de.drick.compose.hotpreview.HotPreview
import de.drick.compose.hotpreview.HotPreviewScreenSizes
import kotlinx.coroutines.isActive
import kotlinx.datetime.DayOfWeek
import org.jetbrains.compose.resources.stringResource

enum class CloudCover {
    CLEAR, CLOUDY_1, CLOUDY_2, CLOUDY_3, OVERCAST;
}

@Composable
fun CloudCover.stringResource() = when (this) {
    CloudCover.CLEAR -> stringResource(Res.string.clear)
    CloudCover.CLOUDY_1 -> stringResource(Res.string.cloudy_1)
    CloudCover.CLOUDY_2 -> stringResource(Res.string.cloudy_2)
    CloudCover.CLOUDY_3 -> stringResource(Res.string.cloudy_3)
    CloudCover.OVERCAST -> stringResource(Res.string.overcast)
}

@Composable
fun DayOfWeek.stringResource() = when (this) {
    DayOfWeek.MONDAY -> stringResource(Res.string.monday)
    DayOfWeek.TUESDAY -> stringResource(Res.string.tuesday)
    DayOfWeek.WEDNESDAY -> stringResource(Res.string.wednesday)
    DayOfWeek.THURSDAY -> stringResource(Res.string.thursday)
    DayOfWeek.FRIDAY -> stringResource(Res.string.friday)
    DayOfWeek.SATURDAY -> "Not supported"
    DayOfWeek.SUNDAY -> "Not supported"
    else -> TODO()
}

data class Weather(val day: DayOfWeek, val clouds: CloudCover, val windSpeedKmh: Int, val rain: Boolean)

@HotPreview(
    "Mango",
    heightDp = 891,
    fontScale = 1.15f,
    density = 2.625f, group = "dark", widthDp = 411
)
@HotPreview(
    "German", widthDp = 411, heightDp = 891, fontScale = 1.50f,
    density = 2.625f, locale = "de", darkMode = false, group = "light"
)
@HotPreviewScreenSizes
@Composable
private fun PreviewForecastView() {
    MyTheme {
        ForecastView()
    }
}
val weatherForecast = listOf(
    Weather(DayOfWeek.MONDAY, CloudCover.CLOUDY_2, 10, false),
    Weather(DayOfWeek.TUESDAY, CloudCover.CLOUDY_3, 30, true),
    Weather(DayOfWeek.WEDNESDAY, CloudCover.CLEAR, 10, false),
    Weather(DayOfWeek.THURSDAY, CloudCover.CLOUDY_1, 60, false),
    Weather(DayOfWeek.FRIDAY, CloudCover.OVERCAST, 100, true),
)

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ForecastView() {
    val test = 11

    var seconds by remember { mutableStateOf(0.0) }
    LaunchedEffect(key1 = test) {
        val startMillis = withFrameMillis { it }
        while (isActive) {
            withInfiniteAnimationFrameMillis {
                val duration = it - startMillis
                seconds = duration.toDouble() / 1000.0
            }
        }
    }
    Scaffold {
        LazyColumn(
            Modifier.fillMaxSize(),
            contentPadding = PaddingValues(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            stickyHeader {
                Box(Modifier.fillMaxWidth().padding(8.dp)) {
                    Text(
                        stringResource(Res.string.title),
                        Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Center
                    )
                }
            }
            items(weatherForecast) { weather ->
                Box(Modifier.clip(RoundedCornerShape(8.dp))) {
                    Row {
                        Column(Modifier.width(130.dp)) {
                            Text(weather.day.stringResource())
                            Text(weather.clouds.stringResource())
                            Text("Wind: ${weather.windSpeedKmh} Km/h")
                            if (weather.rain) Text(stringResource(Res.string.rainy))
                        }
                        Box(Modifier.weight(1f)) {
                            WeatherCanvas(
                                modifier = Modifier.aspectRatio(1f).clip(RoundedCornerShape(8.dp)),
                                seconds = seconds,
                                weather = weather
                            )
                        }
                    }
                }
            }
        }
    }
}

