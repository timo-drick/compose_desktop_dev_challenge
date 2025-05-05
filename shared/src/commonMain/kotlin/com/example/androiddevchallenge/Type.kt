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
package com.example.androiddevchallenge

import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.androiddevchallenge.shared.generated.resources.*
import com.example.androiddevchallenge.shared.generated.resources.Res
import com.example.androiddevchallenge.shared.generated.resources.kulim_park_light
import com.example.androiddevchallenge.shared.generated.resources.kulim_park_regular
import com.example.androiddevchallenge.shared.generated.resources.lato_bold
import org.jetbrains.compose.resources.Font

@Composable
private fun Kulim() = FontFamily(
    Font(Res.font.kulim_park_light, FontWeight.Light),
    Font(Res.font.kulim_park_regular, FontWeight.Normal),
)
@Composable
private fun Lato() = FontFamily(
    Font(Res.font.lato_bold, FontWeight.Bold),
    Font(Res.font.lato_regular, FontWeight.Normal),
)

// Set of Material typography styles to start with
@Composable
fun typography() = Typography(
    titleLarge = TextStyle(
        fontFamily = Kulim(),
        fontWeight = FontWeight.Light,
        fontSize = 28.sp,
        letterSpacing = 1.15.sp
    ),
    titleMedium = TextStyle(
        fontFamily = Kulim(),
        fontWeight = FontWeight.Normal,
        fontSize = 15.sp,
        letterSpacing = 1.15.sp
    ),
    titleSmall = TextStyle(
        fontFamily = Lato(),
        fontWeight = FontWeight.Bold,
        fontSize = 14.sp,
        letterSpacing = 0.sp
    ),
    bodyMedium = TextStyle(
        fontFamily = Lato(),
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp,
        letterSpacing = 0.sp
    ),
    bodyLarge = TextStyle(
        fontFamily = Lato(),
        fontWeight = FontWeight.Bold,
        fontSize = 14.sp,
        letterSpacing = 1.15.sp
    ),
    headlineSmall = TextStyle(
        fontFamily = Kulim(),
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp,
        letterSpacing = 1.15.sp
    )
)
