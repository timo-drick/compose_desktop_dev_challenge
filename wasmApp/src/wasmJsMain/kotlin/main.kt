import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.window.CanvasBasedWindow
import com.example.androiddevchallenge.MyApp
import com.example.androiddevchallenge.devchallenge3.MyTheme

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