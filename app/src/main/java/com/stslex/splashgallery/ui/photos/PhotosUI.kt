package com.stslex.splashgallery.ui.photos

interface PhotosUI {

    fun bind()

    data class Base(
        private val imageId: String,
        private val imageUrl: String,
        private val userId: String,
        private val userName: String,
        private val userUrl: String
    ) : PhotosUI {
        override fun bind() = Unit

    }
}