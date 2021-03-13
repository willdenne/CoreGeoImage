package model

import java.awt.image.BufferedImage
import java.io.File
import java.lang.Exception
import javax.imageio.ImageIO
import java.io.FileNotFoundException
import java.io.PrintWriter
import javax.swing.JFileChooser
import kotlin.math.roundToInt

class CoreImage(
    private val fileName: String,
    private val texts: List<ImageLine>
) {

    var image: BufferedImage? = null

    init {

        image = try {
            ImageIO.read(File(fileName))
        } catch (e: Exception) {
            null
        }
        if (image != null) {
            println("Found file $fileName")
        } else {
            println("Failed to get file, please try again")
        }
    }

    fun update() {
        image = try {
            ImageIO.read(File(fileName))
        } catch (e: Exception) {
            null
        }
        image?.let { bufferedImage ->
            for (x: Int in 0 until bufferedImage.width) {
                if (texts.map { it.getPositionInt() }.contains(x)) {
                    image?.setRGB(
                        x,0,1,bufferedImage.height,arrayOf(255,0,0).toIntArray(),0,0
                    )
                    if (x < bufferedImage.width - 2) {
                        image?.setRGB(
                            x + 1, 0, 1, bufferedImage.height, arrayOf(255, 0, 0).toIntArray(), 0, 0
                        )
                        image?.setRGB(
                            x + 2, 0, 1, bufferedImage.height, arrayOf(255, 0, 0).toIntArray(), 0, 0
                        )
                    }
                }
            }
        }
    }

    fun export() {
        image = try {
            ImageIO.read(File(fileName))
        } catch (e: Exception) {
            null
        }
        val filePicker = JFileChooser()
        val location = if (filePicker.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
            filePicker.selectedFile.path
        } else {
            "coreOutput"
        }
        try {
            PrintWriter(File("$location.csv")).use { writer ->
                val sb = StringBuilder()
                sb.append("Pixel Number,")
                getValidLines().forEach {
                    sb.append("$it,")
                }
                sb.append("\n")
                image?.let { bufferedImage ->
                    for (y: Int in 0 until bufferedImage.height) {
                        sb.append(",")
                        for (x: Int in 0 until bufferedImage.width) {
                            if (getValidLines().contains(x)) {
                                val clr = bufferedImage.getRGB(x, y) and 0x000000ff
                                sb.append("${(clr / 255.0).round(5)},")
                            }
                        }
                        sb.append("\n")
                    }
                }
                writer.write(sb.toString())
                AppState.openDialog.value = true
            }
        } catch (e: FileNotFoundException) {
            println(e.message)
        }
    }

    private fun getValidLines() =
        texts.filter {
            it.visibility.value
        }.map {
            it.getPositionInt()
        }
}

fun Double.round(decimals: Int): Double {
    var multiplier = 1.0
    repeat(decimals) { multiplier *= 10 }
    return (this * multiplier).roundToInt() / multiplier
}
