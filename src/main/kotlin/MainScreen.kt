import androidx.compose.desktop.Window
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.unit.dp
import org.jetbrains.skija.Image


@Composable
fun setScreen() {
    var text by remember { mutableStateOf("Hello, World!") }

    MaterialTheme {
        Column {
            Button(onClick = {
                text = "Hello, Desktop!"
            }) {
                Text(text)
            }
            setSpacer(10)
            Clickable(
                onClick = {

                }
            ) {
                Image(
                    Image.makeFromEncoded(
                        CoreImage("cells.tif").toByteArray()
                    ).asImageBitmap()
                )
            }

        }
    }
}

@Composable
fun setSpacer(height: Int) {
    Spacer(modifier = Modifier.height(height.dp))
}
