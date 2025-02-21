package com.example.androiddevchallenge

import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewFontScale
import androidx.compose.ui.tooling.preview.PreviewScreenSizes
import com.example.androiddevchallenge.devchallenge1.PuppyApp
import com.example.androiddevchallenge.devchallenge1.PuppyTheme
import com.example.androiddevchallenge.devchallenge2.CountPoserApp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyTheme {
                MyApp()
            }
        }
    }
}

@Preview(name="T1", group = "Test", showSystemUi = true,
    device = "spec:width=1080px,height=2340px,dpi=440,orientation=landscape,cutout=punch_hole,navigation=buttons"
)
@Preview(group = "Dark", showSystemUi = true,
    device = "spec:width=1080px,height=2340px,dpi=440,orientation=landscape,cutout=punch_hole,navigation=buttons",
    uiMode = Configuration.UI_MODE_NIGHT_YES or Configuration.UI_MODE_TYPE_NORMAL
)
@PreviewScreenSizes
@PreviewFontScale
@Composable
fun TestPreview() {
    PuppyTheme {
        CountPoserApp()
    }
}