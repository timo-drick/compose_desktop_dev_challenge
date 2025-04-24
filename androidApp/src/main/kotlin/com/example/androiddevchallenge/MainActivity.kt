package com.example.androiddevchallenge

import android.content.res.Configuration
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewFontScale
import androidx.compose.ui.tooling.preview.PreviewScreenSizes
import com.example.androiddevchallenge.devchallenge1.PuppyApp
import com.example.androiddevchallenge.devchallenge3.HomeScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        if (Build.VERSION.SDK_INT >= 29) {
            window.isNavigationBarContrastEnforced = false
        }
        super.onCreate(savedInstanceState)
        setContent {
            MyApp()
        }
    }
}

@Preview(
    "T1",
    "Test", fontScale = 1.5f, device = "spec:width=411dp,height=891dp"
)
@Preview(group = "Dark", showSystemUi = true,
    device = "spec:width=1080px,height=2340px,dpi=440,orientation=landscape,cutout=punch_hole,navigation=buttons",
    uiMode = Configuration.UI_MODE_NIGHT_YES or Configuration.UI_MODE_TYPE_NORMAL
)
@PreviewScreenSizes
@PreviewFontScale
@Composable
fun TestPreview() {
    MyTheme {
        HomeScreen()
    }
}

@Preview(name="T1", group = "Test", showSystemUi = true,
    device = "spec:width=1920px,height=1080px,dpi=160"
)
@Composable
fun TestPreview2() {
    MyTheme {
        PuppyApp()
    }
}