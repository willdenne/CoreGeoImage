package pages

import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import model.AppState

@Composable
fun SaveDialog() {
    if (AppState.openDialog.value) {
        AlertDialog(
            onDismissRequest = {
                AppState.openDialog.value = false
            },
            title = {
                Text(text = "Saved successfully")
            },
            text = {
                Text("CSV saved successfully")
            },
            confirmButton = {
                Button(
                    onClick = {
                        AppState.openDialog.value = false
                    }
                ) {
                    Text("OK")
                }
            }
        )
    }
}