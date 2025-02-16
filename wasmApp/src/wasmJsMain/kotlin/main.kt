import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.window.CanvasBasedWindow
import com.example.androiddevchallenge.MyApp
import com.example.androiddevchallenge.MyTheme
import de.drick.compose.hotpreview.HotPreview

@OptIn(ExperimentalComposeUiApi::class)
fun main() {
    CanvasBasedWindow(
        canvasElementId = "ComposeTarget"
    ) {
        MyTheme {
            MyApp()
        }
    }
}

// This annotation will not work because it is Wasm platform
// @HotPreview annotation will only work in common and desktop platform
@HotPreview
@Composable
fun TestNotSupportedPlatformHotPreview() {
    MyApp()
}