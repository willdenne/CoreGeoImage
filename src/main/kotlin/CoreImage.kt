import java.awt.image.BufferedImage
import java.io.ByteArrayOutputStream
import java.io.File
import java.lang.Exception
import javax.imageio.ImageIO

class CoreImage(
    val fileName: String
) {

    var image: BufferedImage? = null
        private set

    init {
        //while (true) {
            //fileName = readLine()
            println("Getting file $fileName")

            image = try {
                ImageIO.read(File("src/main/resources/$fileName"))
            } catch (e: Exception) {
                null
            }
            if (image != null) {
                println("Found file $fileName")
                //break
            }
            println("Failed to get file, please try again")
        //}

//        image?.let { buffered ->
//            for (x: Int in 0 until buffered.width) {
//                for (y: Int in 0 until buffered.height) {
//                    val clr = buffered.getRGB(x,y)
//                    println("($x,$y) ${clr and 0x000000ff}")
//                }
//            }
//        }
    }

    fun toByteArray() : ByteArray {
        val baos = ByteArrayOutputStream()
        ImageIO.write(image, "png", baos)
        return baos.toByteArray()
    }
}