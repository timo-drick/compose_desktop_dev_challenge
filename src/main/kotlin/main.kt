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
import androidx.compose.animation.Crossfade
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.window.singleWindowApplication
import com.example.androiddevchallenge.devchallenge1.PuppyApp
import com.example.androiddevchallenge.devchallenge2.CountPoserApp
import com.example.androiddevchallenge.devchallenge3.*

private var darkMode = mutableStateOf(true)

@Composable
@ReadOnlyComposable
fun isSystemInDarkTheme(): Boolean = darkMode.value

fun setSystemInDarkTheme(darkTheme: Boolean) {
    darkMode.value = darkTheme
}

sealed class Screen {
    object Start : Screen()
    object Login : Screen()
    object Home : Screen()
    object Puppy : Screen()
    object CountPoser : Screen()
}

fun main() = singleWindowApplication {
    MyTheme(darkTheme = isSystemInDarkTheme()) {
        MyApp()
    }
}

@Composable
fun MyApp() {

    var currentScreen by remember { mutableStateOf<Screen>(Screen.Start) }

    Crossfade(targetState = currentScreen) { screen ->
        when (screen) {
            Screen.Start -> StartScreen(onContinue = {
                currentScreen = it
            })
            Screen.Login -> LoginScreen(onLogin = { currentScreen = Screen.Home })
            Screen.Home -> HomeScreen()
            Screen.Puppy -> PuppyApp()
            Screen.CountPoser -> CountPoserApp()
        }
    }
}

class GreetingService {
    fun getGreeting(callback: (Result<String>) -> Unit) {
        callback(Result.success("Hi there"))
    }
}
@Composable
fun Greeting(greetingService: GreetingService) {
    var greeting: String by remember { mutableStateOf("") }
    DisposableEffect(Unit) {
        greetingService.getGreeting { result ->
            result.fold(
                onSuccess = { greeting = it },
                onFailure = { greeting = "NOOO" }
            )
        }
        onDispose {  }
    }
    Text(text = greeting)
}