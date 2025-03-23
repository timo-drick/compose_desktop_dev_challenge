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

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import kotlin.math.floor
import kotlin.math.pow
import kotlin.math.sin

private val sunGradient = listOf(Color.Yellow, Color.Yellow.copy(alpha = 0.3f))
private val coronaGradient = listOf(Color.Yellow.copy(alpha = 0.7f), Color.Yellow.copy(alpha = 0.0f))

fun DrawScope.sun(seconds: Double) {
    val coronaIntensity = ((sin(seconds * 2.0) + 1.0) / 2.0).toFloat()
    val rCorona = .9f + 0.1f * coronaIntensity
    drawCircle(brush = Brush.radialGradient(coronaGradient, Offset.Zero, rCorona), rCorona, Offset.Zero)

    // Main sun
    val intensity = ((sin(seconds * 3.0) + 1.0) / 2.0).toFloat()
    val r = 0.5f + 0.1f * intensity
    drawCircle(brush = Brush.radialGradient(sunGradient, Offset.Zero, r), r, Offset.Zero)
}

fun DrawScope.sunnyWeather(seconds: Double, clouds: Int, wind: Float) {
    sun(seconds)
    if (clouds > 0) {
        drawContext.canvas.save()
        drawContext.canvas.translate(0f, 0.4f)
        clouds(seconds, clouds, wind, false)
        drawContext.canvas.restore()
    }
}

private val rainCloudGradient = listOf(Color.Gray, Color.Gray, Color.Gray.copy(alpha = 0.3f))
private val cloudGradient = listOf(Color.White, Color.White, Color.White.copy(alpha = 0.3f))

fun normalizeTime1(time: Double) = ((time * 1000.0 % 1000.0) / 1000.0).toFloat()
fun normalizeTimeX(time: Double) = (((time * 1000.0 % 1000.0) / 1000.0) * 4.0 - 2.0).toFloat()

fun Vec2.toOffset(): Offset = Offset(x.toFloat(), y.toFloat())

fun DrawScope.clouds(seconds: Double, clouds: Int, wind: Float, rain: Boolean) {
    val canvas = drawContext.canvas
    repeat(clouds) { index ->
        val n1 = noise1(index.toFloat() * 21)
        val n2 = noise1(index.toFloat() * 2452)
        val posX = normalizeTimeX(seconds * wind * 0.5 + n1)
        canvas.save()
        canvas.translate(posX, n2 * 0.5f)
        canvas.scale(0.6f, 0.6f)
        if (rain) {
            rainCloud(seconds, wind)
        } else {
            cloud()
        }
        canvas.restore()
    }
}

fun DrawScope.cloud() {
    val canvas = drawContext.canvas
    canvas.save()
    canvas.translate(0f, -1f)
    canvas.scale(1f, 0.5f)
    drawCircle(Brush.radialGradient(cloudGradient, Offset.Zero, 1f), 1f, Offset.Zero)
    canvas.restore()
}

fun DrawScope.rainCloud(seconds: Double, wind: Float) {
    repeat(200) { index ->
        val n1 = noise1(index.toFloat())
        val n2 = (noise1(index.toFloat() * 1434) * 0.9f) + 0.1f
        val time = seconds + n1 * 243
        val xOffset = n1 * 2f - 1f
        val speed = n2.pow(3f) * 0.9f + 0.1f
        rain(time, xOffset, speed, wind)
    }
    val canvas = drawContext.canvas
    canvas.save()
    canvas.translate(0f, -1f)
    canvas.scale(1f, 0.5f)
    drawCircle(Brush.radialGradient(rainCloudGradient, Offset.Zero, 1f), 1f, Offset.Zero)
    canvas.restore()
}

fun DrawScope.rain(seconds: Double, xOffset: Float, speed: Float, wind: Float) {
    val yPos = (((seconds * 100.0 * speed) % 100) / 50f).toFloat()

    val direction = Vec2(2f * wind, 1f).normalized
    val xPos = (direction.x / direction.y) * yPos + xOffset
    val start = Vec2(xPos, yPos - 1f)
    val end = start + direction * (speed * 0.05f)
    drawLine(color = Color.Blue, start.toOffset(), end.toOffset(), strokeWidth = (0.01 * speed).toFloat())
}

fun smoothstep1(x: Float) = x * x * (3f - 2f * x)
fun mix(a: Float, b: Float, x: Float) = (1f - x) * a + x * b
fun fract(a: Float): Float = a - floor(a)

fun noise1(a: Float): Float = fract(sin(a * 100f) * 5647f)
fun noise21(a: Float, b: Float): Float = fract(sin(a * 100f + b * 6574f) * 5647f)

fun fract(a: Double): Double = a - floor(a)
