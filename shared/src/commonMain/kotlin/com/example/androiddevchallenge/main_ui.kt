package com.example.androiddevchallenge

import androidx.compose.animation.Crossfade
import androidx.compose.material.Text
import androidx.compose.runtime.*
import com.example.androiddevchallenge.devchallenge1.PuppyApp
import com.example.androiddevchallenge.devchallenge2.CountPoserApp
import com.example.androiddevchallenge.devchallenge3.HomeScreen
import com.example.androiddevchallenge.devchallenge3.LoginScreen
import com.example.androiddevchallenge.devchallenge3.StartScreen


sealed class Screen {
    data object Start : Screen()
    data object Login : Screen()
    data object Home : Screen()
    data object Puppy : Screen()
    data object CountPoser : Screen()
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