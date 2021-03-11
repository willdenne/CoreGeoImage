package pages

import CoreImage
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.unit.dp
import model.AppState
import model.Content
import model.ScreenType
import org.jetbrains.skija.Image as JImage

@Composable
fun imagePage(content: Content) {

    MaterialTheme {
        Button(
            onClick = {
                AppState.screenState(ScreenType.File)
            }, modifier = Modifier.padding(16.dp)
        ) {
            Text("Go back")
        }
        Column(
            modifier = Modifier.fillMaxWidth().fillMaxHeight(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                bitmap = JImage.makeFromEncoded(
                    CoreImage(content.imageLocation).toByteArray()
                ).asImageBitmap(),
                contentDescription = null
            )
            setSpacer(10)
            Row {
                Text("Text #1")
                Text("Text #2")
                Text("Text #3")
            }

        }
    }
}

@Composable
fun setSpacer(height: Int) {
    Spacer(modifier = Modifier.height(height.dp))
}
