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

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.paddingFromBaseline
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import isSystemInDarkTheme

@Composable
fun LoginScreen(onLogin: () -> Unit) {
    val prefix = if (isSystemInDarkTheme()) "svgs/dark_" else "svgs/light_"
    val background = painterResource("${prefix}login.xml")

    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colors.background
    ) {
        Image(
            background,
            "background",
            Modifier.fillMaxSize(),
            contentScale = ContentScale.FillBounds
        )
        Column(Modifier.padding(screenPadding), horizontalAlignment = Alignment.CenterHorizontally) {
            Text("LOG IN", Modifier.paddingFromBaseline(top = 200.dp, bottom = 32.dp), style = MaterialTheme.typography.h1)
            TextField(
                value = email,
                modifier = Modifier.fillMaxWidth(),
                onValueChange = { email = it },
                placeholder = {
                    Text("Email address")
                },
                colors = TextFieldDefaults.textFieldColors(backgroundColor = MaterialTheme.colors.surface)
            )
            Spacer(Modifier.height(8.dp))
            TextField(
                value = password,
                modifier = Modifier.fillMaxWidth(),
                onValueChange = { password = it },
                placeholder = {
                    Text("Password")
                },
                colors = TextFieldDefaults.textFieldColors(backgroundColor = MaterialTheme.colors.surface)
            )
            Spacer(Modifier.height(8.dp))
            Button(
                onClick = onLogin,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(72.dp),
                shape = MaterialTheme.shapes.medium
            ) {
                Text(text = "LOG IN")
            }
            val text = remember {
                buildAnnotatedString {
                    append("Don't have an account? ")
                    withStyle(SpanStyle(textDecoration = TextDecoration.Underline)) {
                        append("Sign up")
                    }
                }
            }
            Text(text, Modifier.paddingFromBaseline(32.dp))
        }
    }
}
