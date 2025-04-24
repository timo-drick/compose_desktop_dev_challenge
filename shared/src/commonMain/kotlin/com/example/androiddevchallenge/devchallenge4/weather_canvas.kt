package com.example.androiddevchallenge.devchallenge4

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import de.drick.compose.hotpreview.HotPreview
import de.drick.compose.hotpreview.HotPreviewParameter
import de.drick.compose.hotpreview.HotPreviewParameterProvider

val skyColor = Color(0xFF9FF3FF)
val darkSkyColor = Color(0xFFC0DDE2)


class WeatherProvider : HotPreviewParameterProvider<Weather> {
    override val values: Sequence<Weather> = weatherForecast.asSequence()
}

@HotPreview(widthDp = 200, heightDp = 200)
@Composable
private fun PreviewWeatherCanvas(
    @HotPreviewParameter(WeatherProvider::class) weather: Weather
) {
    WeatherCanvas(
        modifier = Modifier.fillMaxSize(),
        seconds = 20.0,
        weather = weather,
    )
}

@Composable
fun WeatherCanvas(
    seconds: Double,
    weather: Weather,
    modifier: Modifier = Modifier
) {
    val clouds = when (weather.clouds) {
        CloudCover.CLEAR -> 0
        CloudCover.CLOUDY_1 -> 1
        CloudCover.CLOUDY_2 -> 2
        CloudCover.CLOUDY_3 -> 3
        CloudCover.OVERCAST -> 4
    }
    val wind = (weather.windSpeedKmh.toFloat() / 150f).coerceIn(0.1f, 1f)
    val sunnyWeather = clouds < 3 && weather.rain.not()
    Canvas(
        modifier = modifier.background(if (sunnyWeather) skyColor else darkSkyColor)
    ) {
        val canvas = drawContext.canvas
        val scale = size.minDimension / 2f
        canvas.translate(center.x, center.y)
        canvas.scale(scale, scale)
        if (sunnyWeather) {
            sunnyWeather(seconds, clouds, wind)
        } else {
            clouds(seconds, clouds, wind, weather.rain)
        }
    }
}