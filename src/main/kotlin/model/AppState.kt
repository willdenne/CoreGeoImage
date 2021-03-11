package model

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf

enum class ScreenType {
    File, Image
}

object AppState {
    private var screen: MutableState<ScreenType> = mutableStateOf(ScreenType.File)

    fun screenState() : ScreenType {
        return screen.value
    }

    fun screenState(state: ScreenType) {
        screen.value = state
    }
}