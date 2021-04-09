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
import androidx.compose.desktop.Window
import androidx.compose.material.Text
import androidx.compose.runtime.*
import com.example.androiddevchallenge.devchallenge1.PuppyApp
import com.example.androiddevchallenge.devchallenge2.CountPoserApp
import com.example.androiddevchallenge.devchallenge3.*
import de.appsonair.compose.live_composable.LiveComposable
import java.io.File

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

fun main() = Window {
    MyTheme(darkTheme = isSystemInDarkTheme()) {
        MyApp()
    }
}

@Composable
fun MyApp() {

    var currentScreen by remember { mutableStateOf<Screen>(Screen.Start) }

    Crossfade(targetState = currentScreen) { screen ->
        when (screen) {
            Screen.Start -> LiveComposable<LiveStartScreen, IStartScreen>(File("src/main/kotlin/com/example/androiddevchallenge/devchallenge3/start_screen.kt")) {
                start(onContinue = {
                    currentScreen = it
                })
            }
            Screen.Login -> LiveComposable<LiveLoginScreen, ILoginScreen>(File("src/main/kotlin/com/example/androiddevchallenge/devchallenge3/login_screen.kt")) {
                start(onLogin = { currentScreen = Screen.Home })
            }
            Screen.Home -> LiveComposable<LiveHomeScreen>(File("src/main/kotlin/com/example/androiddevchallenge/devchallenge3/home_screen.kt"))
            Screen.Puppy -> PuppyApp()
            Screen.CountPoser -> CountPoserApp()
        }
    }
}

interface IStartScreen {
    @Composable fun start(onContinue: (Screen) -> Unit)
}

interface ILoginScreen {
    @Composable fun start(onLogin: () -> Unit)
}