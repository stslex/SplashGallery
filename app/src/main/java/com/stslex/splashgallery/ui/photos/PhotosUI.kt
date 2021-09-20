package com.stslex.splashgallery.ui.photos

import com.stslex.splashgallery.ui.core.AbstractView
import com.stslex.splashgallery.ui.core.CustomImageView

interface PhotosUI {

    fun bindPhotos(
        image: AbstractView.Image,
        avatar: AbstractView.Image,
        username: AbstractView.Text,
        cardView: AbstractView.Card
    )

    fun openDetail(function: (CustomImageView, String) -> Unit) = Unit

    data class Base(
        val imageId: String,
        val imageUrl: String,
        val userId: String,
        val userName: String,
        val userUrl: String
    ) : PhotosUI {
        override fun bindPhotos(
            image: AbstractView.Image,
            avatar: AbstractView.Image,
            username: AbstractView.Text,
            cardView: AbstractView.Card
        ) {
            image.load(imageUrl, needCrop = true)
            avatar.load(userId, needCircle = true)
            username.map(userName)
            cardView.transit(userId)
        }

        override fun openDetail(function: () -> Unit) = Unit

    }
}