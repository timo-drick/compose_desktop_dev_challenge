package com.example.androiddevchallenge.devchallenge2

import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.updateTransition
import androidx.compose.desktop.Window
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.runtime.withFrameMillis
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import com.example.androiddevchallenge.ui.MyTheme
import isSystemInDarkTheme
import kotlinx.coroutines.launch

fun main() = Window {
    MyTheme(darkTheme = true) {
        CountPoserApp()
    }
}


enum class TimerState {
    New, Old, Finish
}


// Start building your app here!
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun CountPoserApp() {
    var countDownMax by remember { mutableStateOf(10) }
    val baseTextSize = 64.sp
    val gradientBase = listOf(Color.Red, Color.Red.copy(alpha = 0f))
    val gradientFilled = listOf(Color.Green, Color.Green.copy(alpha = 0f))
    val blurRadius = with(LocalDensity.current) { 24.dp.toPx() }
    val counterTextStyle = MaterialTheme.typography.h1.copy(
        shadow = Shadow(blurRadius = blurRadius, color = if (isSystemInDarkTheme()) Color.Black else Color.White)
    )
    var counter by remember { mutableStateOf(countDownMax) }
    var dialogVisible by remember { mutableStateOf(false) }
    if (dialogVisible) {
        SetTimerDialog(
            currentSeconds = countDownMax,
            newSecondsSet = {
                dialogVisible = false
                if (it != null) {
                    countDownMax = it
                    counter = countDownMax
                }
            }
        )
    }
    Surface(Modifier.fillMaxSize(), color = MaterialTheme.colors.background) {
        var counterMillis by remember { mutableStateOf(0L) }
        var state by remember { mutableStateOf(TimerState.Old) }
        var isRunning by remember { mutableStateOf(false) }
        val scope = rememberCoroutineScope()
        val transition = updateTransition(state)
        val textSizeMultiplier = transition.animateFloat {
            when (it) {
                TimerState.New -> 3f
                TimerState.Old -> 1f
                TimerState.Finish -> 4f
            }
        }
        val circleSize = transition.animateFloat {
            when (it) {
                TimerState.New -> .7f
                TimerState.Old -> .9f
                TimerState.Finish -> 3f
            }
        }
        fun startCounter() {
            scope.launch {
                if (isRunning) { // Stop counter
                    isRunning = false
                    withFrameMillis { it } // wait for next frame
                    state = TimerState.Old
                    counter = countDownMax
                    counterMillis = 0
                } else {
                    isRunning = true
                    counter = countDownMax
                    val startTime = withFrameMillis { it }
                    while (counter > 0 && isRunning) {
                        val elapsedTime = withFrameMillis { it } - startTime
                        counterMillis = elapsedTime
                        val newState =
                            if (counterMillis % 1000 < 200) TimerState.New else TimerState.Old
                        if (newState != state) {
                            state = newState
                        }
                        val countDown = countDownMax - (elapsedTime / 1000).toInt()
                        if (countDown != counter) {
                            counter = countDown
                        }
                    }
                    if (isRunning) {
                        state = TimerState.Finish
                    }
                }
            }
        }
        Box(
            Modifier
                .fillMaxSize()
                .combinedClickable(onClick = { startCounter() }, onLongClick = { if (isRunning.not()) dialogVisible = true })
        ) {
            if (isRunning.not()) {
                Column(Modifier.align(Alignment.TopCenter), horizontalAlignment = Alignment.CenterHorizontally) {
                    Text("Tap anywhere to start", Modifier.padding(8.dp))
                    Text(
                        "Long tap anywhere to set timer",
                        Modifier.padding(8.dp)
                    )
                }
            }
            Canvas(modifier = Modifier.fillMaxSize()) {
                val d = size.minDimension * circleSize.value
                val r = d / 2f
                val offset = Offset((size.width / 2f - r), size.height / 2f - r)
                drawCircle(
                    brush = Brush.radialGradient(gradientBase, radius = r),
                    radius = r,
                    center = center
                )
                val angleDegree = (counterMillis.toFloat() / (1000f * countDownMax)) * 360f

                drawArc(
                    brush = Brush.radialGradient(gradientFilled, radius = r),
                    size = Size(d, d),
                    topLeft = offset,
                    startAngle = -90f,
                    sweepAngle = angleDegree,
                    useCenter = true
                )
                val br = size.minDimension * 0.13f
                if (counter > 0) {
                    drawCircle(
                        brush = Brush.radialGradient(gradientBase, radius = br * 2f),
                        radius = br,
                        center = center
                    )
                }
            }
            Text(
                text = counter.toString(),
                modifier = Modifier.align(Alignment.Center),
                fontSize = baseTextSize * textSizeMultiplier.value,
                style = counterTextStyle

            )
        }
    }
}

@Composable
fun SetTimerDialog(currentSeconds: Int, newSecondsSet: (seconds: Int?) -> Unit) {
    var textValue by remember { mutableStateOf(currentSeconds.toString()) }
    Box(Modifier
        .fillMaxSize()
        .zIndex(10f)
        .background(Color.Black.copy(alpha = 0.75f))
        .clickable(onClick = { newSecondsSet(null) }),
        contentAlignment = Alignment.Center
    ) {
        Surface(shape = MaterialTheme.shapes.medium) {
            Column(Modifier.padding(16.dp)) {
                Text("Set count down in seconds.", Modifier.padding(bottom = 4.dp))
                TextField(
                    value = textValue,
                    onValueChange = { textValue = it },
                    keyboardOptions = KeyboardOptions(
                        autoCorrect = false,
                        keyboardType = KeyboardType.Number,
                        imeAction = ImeAction.Done
                    ),
                    keyboardActions = KeyboardActions(onDone = { newSecondsSet(textValue.toInt()) })
                )
                Row {
                    TextButton(onClick = { newSecondsSet(textValue.toInt()) }) {
                        Text("Set")
                    }
                    TextButton(onClick = { newSecondsSet(null) }) {
                        Text("Cancel")
                    }
                }
            }
        }
    }
}
