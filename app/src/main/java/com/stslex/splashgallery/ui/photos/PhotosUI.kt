package com.stslex.splashgallery.ui.photos

interface PhotosUI {

    fun map()

    data class Base(
        val id: String
    ) : PhotosUI {
        override fun map() {
            TODO("Not yet implemented")
        }

    }
}