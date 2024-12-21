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
package com.example.androiddevchallenge.devchallenge3

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Shapes
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

/**
 * apps on air
 *
 * @author Timo Drick
 */
val gray800 = Color(0xFF333333).copy(alpha = 0.8f)
val gray900 = Color(0xFF333333)
val rust600 = Color(0xFF886363)
val rust300 = Color(0xFFE1AFAF)
val taupe100 = Color(0xFFF0EAE2)
val taupe800 = Color(0xFF655454)
val white150 = Color.White.copy(alpha = 0.15f)
val white800 = Color.White.copy(alpha = 0.80f)
val white850 = Color.White.copy(alpha = 0.85f)

val DarkColorPalette = darkColors(
    primary = Color.White,
    secondary = rust300,
    background = gray900,
    surface = white150,
    onPrimary = gray900,
    onSecondary = gray900,
    onBackground = taupe100,
    onSurface = white800
)

val LightColorPalette = lightColors(
    primary = gray900,
    secondary = rust600,
    background = taupe100,
    surface = white850,
    onPrimary = Color.White,
    onSecondary = Color.White,
    onBackground = taupe800,
    onSurface = gray800
)

val shapes = Shapes(
    small = RoundedCornerShape(4.dp),
    medium = RoundedCornerShape(16.dp),
    large = RoundedCornerShape(0.dp)
)

val screenPadding = PaddingValues(start = 16.dp, end = 16.dp)

val bottomNavigationElevation = 8.dp

@Composable
fun MyTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }
    MaterialTheme(
        colors = colors,
        typography = typography(),
        shapes = shapes,
        content = content
    )
}
