package com.example.androiddevchallenge.devchallenge1

import androidx.compose.animation.*
import androidx.compose.animation.core.snap
import androidx.compose.animation.core.tween

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.geometry.Rect
import com.example.androiddevchallenge.MyTheme
import de.drick.compose.hotpreview.HotPreview


sealed class Screen
data object OverviewScreen : Screen()
data class DetailScreen(val puppy: Puppy, val imagePosition: Rect) : Screen()

@HotPreview(name = "test", group = "te", widthDp = 673, density = 1.0f, heightDp = 841)
@HotPreview(name = "big font", widthDp = 892, heightDp = 411, fontScale = 1.5f, density = 2.625f)
@Composable
fun PreviewPuppyApp() {
    MyTheme {
        PuppyApp()
    }
}

@Composable
fun PuppyApp() {
    var screen by remember { mutableStateOf<Screen>(OverviewScreen) }
    val insets = WindowInsets.safeDrawing
    MyTheme {
        AnimatedContent(
            targetState = screen,
            transitionSpec = {
                fadeIn(tween(100)) togetherWith fadeOut(snap(500))
            }
        ) { targetScreen ->
            when (targetScreen) {
                is OverviewScreen -> Overview(
                    contentPadding = insets.asPaddingValues(),
                    onSelect = { puppy, imagePosition ->
                        screen = DetailScreen(puppy, imagePosition)
                    }
                )
                is DetailScreen -> PuppyDetailStatic(
                    params = targetScreen,
                    onBack = { screen = OverviewScreen }
                )
            }
        }
    }
}