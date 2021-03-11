package model

import androidx.compose.runtime.RememberObserver

object Content : RememberObserver {
    override fun onAbandoned() = Unit
    override fun onForgotten() = Unit
    override fun onRemembered() = Unit

    lateinit var imageLocation: String

    fun getContent(): Content {
        return this
    }
}