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
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import com.example.androiddevchallenge.MyTheme
import com.example.androiddevchallenge.screenPadding
import com.example.androiddevchallenge.shared.generated.resources.Res
import com.example.androiddevchallenge.shared.generated.resources.login
import de.drick.compose.hotpreview.HotPreviewFontScale
import de.drick.compose.hotpreview.HotPreviewLightDark
import de.drick.compose.hotpreview.HotPreviewScreenSizes
import org.jetbrains.compose.resources.painterResource


@PreviewCollection
@HotPreviewFontScale
@HotPreviewLightDark
@Composable
fun LoginPreview() {
    MyTheme {
        LoginScreen(onLogin = {})
    }
}

@Composable
fun LoginScreen(onLogin: () -> Unit) {

    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Image(
            painterResource(Res.drawable.login),
            "background",
            Modifier.fillMaxSize(),
            contentScale = ContentScale.FillBounds
        )
        Column(Modifier.padding(screenPadding), horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                modifier = Modifier.paddingFromBaseline(top = 200.dp, bottom = 32.dp),
                text = "LOG IN",
                style = MaterialTheme.typography.titleLarge
            )
            TextField(
                value = email,
                modifier = Modifier.fillMaxWidth(),
                onValueChange = { email = it },
                placeholder = {
                    Text("Email address")
                }
            )
            Spacer(Modifier.height(8.dp))
            TextField(
                value = password,
                modifier = Modifier.fillMaxWidth(),
                onValueChange = { password = it },
                placeholder = {
                    Text("Password")
                }
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
