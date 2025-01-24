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

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.window.singleWindowApplication
import com.example.androiddevchallenge.MyApp
import com.example.androiddevchallenge.devchallenge3.*
import de.drick.compose.hotpreview.HotPreview


@HotPreview
@HotPreview(darkMode = false)
annotation class TestPreviews //Not working yet


@HotPreview
@HotPreview(darkMode = false)
@Composable
fun TestPreview() {
    MyTheme {
        MyApp()
    }
}

// This is not working yet!!!
@TestPreviews
@Composable
fun ClickableHtmlText(
    text: String,
    textColor: Color?,
    keyHtmlMap: Map<String, String>,
    htmlResult: (String) -> Unit,
) {
    println("Hello World")
}

fun main() {
    singleWindowApplication {
        MyTheme {
            MyApp()
        }
    }
}
