package com.stslex.splashgallery.ui.photos

interface PhotosUI {

    fun bind()
    fun getItems(): PhotosUI.Base

    data class Base(
        val imageId: String,
        val imageUrl: String,
        val userId: String,
        val userName: String,
        val userUrl: String
    ) : PhotosUI {
        override fun bind() = Unit
        override fun getItems(): Base = Base(
            imageId, imageUrl, userId, userName, userUrl
        )

    }
}