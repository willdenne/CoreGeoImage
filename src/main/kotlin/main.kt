import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.desktop.DesktopTheme
import androidx.compose.desktop.Window
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.remember
import androidx.compose.ui.unit.IntSize
import model.AppState
import model.Content
import model.ScreenType
import pages.filePage
import pages.imagePage

@ExperimentalAnimationApi
fun main() {
    Window(
        title = "CoreGeoImage for Desktop",
        size = IntSize(1500, 1000)
    ) {
        val content = remember {
            Content.getContent()
        }
        MaterialTheme {
            DesktopTheme {
                when (AppState.screenState()) {
                    ScreenType.File -> {
                        println("screen file")
                        filePage(content)
                    }
                    ScreenType.Image -> {
                        println("screen image")
                        imagePage(content)
                    }
                }
            }
        }
    }
}
