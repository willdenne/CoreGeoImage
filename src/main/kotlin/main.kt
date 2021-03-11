import androidx.compose.desktop.DesktopTheme
import androidx.compose.desktop.Window
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.remember
import model.AppState
import model.Content
import model.ScreenType
import pages.filePage
import pages.imagePage

fun main() {
    Window( title = "CoreGeoImage for Desktop")  {
        val content = remember {
            Content.getContent()
        }
        MaterialTheme {
            DesktopTheme {
                when (AppState.screenState()) {
                    ScreenType.File -> {
                        filePage(content)
                    }
                    ScreenType.Image -> {
                        imagePage(content)
                    }
                }

            }
        }
    }
}
