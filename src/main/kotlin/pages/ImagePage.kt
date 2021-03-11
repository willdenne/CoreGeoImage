package pages

import Clickable
import CoreImage
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.unit.dp
import model.Content
import org.jetbrains.skija.Image as JImage

@Composable
fun imagePage(content: Content) {
    var text by remember { mutableStateOf("Hello, World!") }

    MaterialTheme {
        Column(
            modifier = Modifier.fillMaxWidth().fillMaxHeight(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Button( onClick = {
                text = "Hello, Desktop!"
            }) {
                Text(text)
            }
            setSpacer(10)
            Image(
                bitmap = JImage.makeFromEncoded(
                    CoreImage(content.imageLocation).toByteArray()
                ).asImageBitmap(),
                contentDescription = null
            )
        }
    }
}

@Composable
fun setSpacer(height: Int) {
    Spacer(modifier = Modifier.height(height.dp))
}
