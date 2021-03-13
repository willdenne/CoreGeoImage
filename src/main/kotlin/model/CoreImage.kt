package model

import java.awt.image.BufferedImage
import java.io.File
import java.lang.Exception
import javax.imageio.ImageIO

class CoreImage(
    private val fileName: String,
    private val texts: List<ImageLine>
) {

    var image: BufferedImage? = null

    init {
        println("Getting file $fileName")

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

//        image?.let { buffered ->
//            for (x: Int in 0 until buffered.width) {
//                for (y: Int in 0 until buffered.height) {
//                    val clr = buffered.getRGB(x,y)
//                    println("($x,$y) ${clr and 0x000000ff}")
//                }
//            }
//        }
    }

    fun update() {
        println("update")
        image = try {
            ImageIO.read(File(fileName))
        } catch (e: Exception) {
            null
        }
        image?.let { bufferedImage ->
            for (x: Int in 0 until bufferedImage.width) {
                if (texts.map { it.getPositionInt() }.contains(x)) {
                    println("recolor at $x")
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

    }
}
