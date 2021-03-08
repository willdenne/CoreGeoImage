import androidx.compose.desktop.DesktopTheme
import androidx.compose.desktop.Window
import androidx.compose.material.MaterialTheme


fun main() {
        Window( title = "CoreGeoImage for Desktop")  {
            MaterialTheme {
                DesktopTheme {
                    setScreen()
                }
            }
        }
}
