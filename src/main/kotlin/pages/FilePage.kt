package pages

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import model.AppState
import model.Content
import model.ScreenType
import javax.swing.JFileChooser

@Composable
fun filePage(content: Content) {
    var text = ""
    Column(
        modifier = Modifier.fillMaxWidth().fillMaxHeight(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(
            onClick = {
                val filePicker = JFileChooser()
                if (filePicker.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
                    content.imageLocation = filePicker.selectedFile.path
                    AppState.screenState(ScreenType.Image)
                } else {
                    text = "Oops, didn't select a valid file"
                }
            }
        ) {
            Text("Choose a file")
        }
        Text(text)
    }
}
