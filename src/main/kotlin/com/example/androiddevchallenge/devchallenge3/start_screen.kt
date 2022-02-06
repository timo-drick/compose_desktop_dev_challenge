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

import Screen
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import isSystemInDarkTheme

enum class Action {
    CONTINUE, COUNT_POSER, PUPPY
}

@Composable
fun StartScreen(onContinue: (Screen) -> Unit) {
    val prefix = if (isSystemInDarkTheme()) "svgs/dark_" else "svgs/light_"
    val backgroundImage = painterResource("${prefix}login.xml")
    val logo = painterResource("${prefix}logo.svg")

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.background),
        contentAlignment = Alignment.Center
    ) {
        Image(
            backgroundImage,
            "background",
            Modifier.fillMaxSize(),
            contentScale = ContentScale.FillBounds
        )
        Column(Modifier.padding(horizontal = 16.dp), horizontalAlignment = Alignment.CenterHorizontally) {
            Image(logo, "mysoothe")
            Spacer(Modifier.height(32.dp))
            Button(
                onClick = { onContinue(Screen.Puppy) },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(72.dp),
                shape = MaterialTheme.shapes.medium,
                colors = ButtonDefaults.buttonColors(MaterialTheme.colors.secondary)
            ) {
                Text(text = "PUPPY")
            }
            Spacer(Modifier.height(8.dp))
            Button(
                onClick = { onContinue(Screen.CountPoser) },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(72.dp),
                shape = MaterialTheme.shapes.medium,
                colors = ButtonDefaults.buttonColors(MaterialTheme.colors.secondary)
            ) {
                Text(text = "COUNT POSER")
            }
            Spacer(Modifier.height(8.dp))
            Button(
                onClick = { onContinue(Screen.Login) },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(72.dp),
                shape = MaterialTheme.shapes.medium
            ) {
                Text(text = "CONTINUE")
            }
        }
    }
}
