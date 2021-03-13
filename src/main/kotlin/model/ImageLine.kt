package model

import androidx.compose.runtime.mutableStateOf

class ImageLine {

    var visibility = mutableStateOf(false)

    var position = mutableStateOf("")

    fun getPositionInt() = try {
        position.value.toInt()
    } catch (e: Exception) {
        -1
    }
}
