package pages

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import model.*
import java.awt.image.BufferedImage
import java.io.ByteArrayOutputStream
import java.lang.Exception
import javax.imageio.ImageIO
import org.jetbrains.skija.Image as JImage

@ExperimentalAnimationApi
@Composable
fun imagePage(content: Content) {

    val texts = remember {
        listOf(ImageLine(), ImageLine(), ImageLine(), ImageLine(), ImageLine())
    }

    val visibleBoxes = mutableStateOf(0)
    val coreImage = CoreImage(content.imageLocation, texts)

    val hackText = mutableStateOf("")

    MaterialTheme {
        Column(
            modifier = Modifier.verticalScroll(
                rememberScrollState()
            )
        ) {
            SaveDialog()
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
                        coreImage.image?.toBufferedImage()
                    ).asImageBitmap(),
                    contentDescription = null,
                    contentScale = ContentScale.FillHeight
                )
                setSpacer(10, 0)
                Text(
                    text = "Image is Height: ${coreImage.image?.height} Width: ${coreImage.image?.width}",
                    modifier = Modifier.padding(16.dp)
                )
                Text(
                    text = hackText.value,
                    modifier = Modifier.size(1.dp)
                )
                Row {
                    textBox(
                        texts[0],
                        "Line one",
                        coreImage
                    ) {
                        if (hackText.value.isEmpty()) {
                            hackText.value = "_"
                        } else {
                            hackText.value = ""
                        }
                    }
                    textBox(
                        texts[1],
                        "Line two",
                        coreImage
                    ) {
                        if (hackText.value.isEmpty()) {
                            hackText.value = "_"
                        } else {
                            hackText.value = ""
                        }
                    }
                    textBox(
                        texts[2],
                        "Line three",
                        coreImage
                    ) {
                        if (hackText.value.isEmpty()) {
                            hackText.value = "_"
                        } else {
                            hackText.value = ""
                        }
                    }
                    textBox(
                        texts[3],
                        "Line four",
                        coreImage
                    ) {
                        if (hackText.value.isEmpty()) {
                            hackText.value = "_"
                        } else {
                            hackText.value = ""
                        }
                    }
                    textBox(
                        texts[4],
                        "Line five",
                        coreImage
                    ) {
                        if (hackText.value.isEmpty()) {
                            hackText.value = "_"
                        } else {
                            hackText.value = ""
                        }
                    }
                }
                setSpacer(10, 0)
                Row {
                    Button(
                        onClick = {
                            visibleBoxes.value--
                            texts[visibleBoxes.value].visibility.value = false
                        },
                        modifier = Modifier.padding(16.dp),
                        enabled = visibleBoxes.value > 0
                    ) {
                        Text("Remove line")
                    }
                    Button(
                        onClick = {
                            texts[visibleBoxes.value].visibility.value = true
                            visibleBoxes.value++
                        },
                        modifier = Modifier.padding(16.dp),
                        enabled = visibleBoxes.value < 5
                    ) {
                        Text("Add line")
                    }
                }
                Button(
                    onClick = {
                        coreImage.export()
                    }
                ) {
                    Text("Export to CSV")
                }
            }
        }
    }
}

private fun BufferedImage?.toBufferedImage(): ByteArray? {
    val baos = ByteArrayOutputStream()
    ImageIO.write(this, "png", baos)
    return baos.toByteArray()
}

@Composable
fun setSpacer(height: Int, width: Int) {
    Spacer(modifier = Modifier.height(height.dp).width(width.dp))
}

@ExperimentalAnimationApi
@Composable
fun textBox(imageLine: ImageLine, text: String, coreImage: CoreImage, callback: () -> Unit) {
    Row {
        AnimatedVisibility(
            visible = imageLine.visibility.value
        ) {
            Text(text)
            TextField(
                value = imageLine.position.value,
                onValueChange = {
                    if (it.isEmpty() || checkIfNumber(it)) {
                        imageLine.position.value = it
                        coreImage.update()
                        callback.invoke()
                    }
                },
                modifier = Modifier.width(124.dp)
            )
        }
    }
    setSpacer(0, 10)
}

private fun checkIfNumber(number: String) = try {
    number.toInt()
    true
} catch (e: Exception) {
    false
}