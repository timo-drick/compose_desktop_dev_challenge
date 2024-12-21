package com.example.androiddevchallenge.devchallenge1

import androidx.compose.animation.*
import androidx.compose.animation.core.FiniteAnimationSpec
import androidx.compose.animation.core.snap
import androidx.compose.animation.core.tween
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.saveable.rememberSaveableStateHolder
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.singleWindowApplication
import de.drick.compose.hotpreview.HotPreview

val purple200 = Color(0xFFBB86FC)
val purple500 = Color(0xFF6200EE)
val purple700 = Color(0xFF3700B3)
val teal200 = Color(0xFF03DAC5)

private val DarkColorPalette = darkColors(
    primary = purple200,
    primaryVariant = purple700,
    secondary = teal200
)

private val LightColorPalette = lightColors(
    primary = purple500,
    primaryVariant = purple700,
    secondary = teal200

    /* Other default colors to override
background = Color.White,
surface = Color.White,
onPrimary = Color.White,
onSecondary = Color.Black,
onBackground = Color.Black,
onSurface = Color.Black,
*/
)

val typography = Typography(
    body1 = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp
    )
    /* Other default text styles to override
button = TextStyle(
    fontFamily = FontFamily.Default,
    fontWeight = FontWeight.W500,
    fontSize = 14.sp
),
caption = TextStyle(
    fontFamily = FontFamily.Default,
    fontWeight = FontWeight.Normal,
    fontSize = 12.sp
)
*/
)

val shapes = Shapes(
    small = RoundedCornerShape(4.dp),
    medium = RoundedCornerShape(4.dp),
    large = RoundedCornerShape(0.dp)
)

@Composable
fun PuppyTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    MaterialTheme(
        colors = colors,
        typography = typography,
        shapes = shapes,
        content = content
    )
}

sealed class Screen
object OverviewScreen : Screen()
data class DetailScreen(val puppy: Puppy, val imagePosition: Rect) : Screen()

@Composable
fun <T> SaveableCrossfade(
    targetState: T,
    modifier: Modifier = Modifier,
    animationSpec: FiniteAnimationSpec<Float> = tween(),
    content: @Composable (T) -> Unit
) {
    val saveableStateHolder = rememberSaveableStateHolder()
    Crossfade(
        targetState = targetState,
        modifier = modifier,
        animationSpec = animationSpec
    ) {
        saveableStateHolder.SaveableStateProvider(it.hashCode()) {
            content(it)
        }
    }
}

fun main() {
    singleWindowApplication {
        PuppyApp()
    }
}

@HotPreview(widthDp = 600, heightDp = 500)
@HotPreview(name = "small font", widthDp = 600, heightDp = 500, fontScale = 0.5f)
@Composable
fun PreviewPuppApp() {
    PuppyTheme {
        PuppyApp()
    }
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun PuppyApp() {
    var screen by remember { mutableStateOf<Screen>(OverviewScreen) }
    PuppyTheme(darkTheme = true) {
        AnimatedContent(
            targetState = screen,
            transitionSpec = {
                fadeIn(tween(100)) with fadeOut(snap(500))
            }
        ) { targetScreen ->
            when (targetScreen) {
                is OverviewScreen -> Overview(
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