package com.stslex.splashgallery.ui.user

import com.stslex.splashgallery.ui.core.AbstractView
import com.stslex.splashgallery.utils.Resources.currentId
import com.stslex.splashgallery.utils.SetImageWithGlide

interface UserUI {

    fun bind(
        glide: SetImageWithGlide,
        usernameTextView: AbstractView.Text,
        profileImageView: AbstractView.Image,
        totalCollectionsTextView: AbstractView.Text,
        totalLikesTextView: AbstractView.Text,
        totalPhotosTextView: AbstractView.Text,
        bioTextView: AbstractView.Text
    )

    data class Base(
        private val username: String,
        private val url: String,
        private val totalCollections: String,
        private val totalPhotos: String,
        private val totalLikes: String,
        private val bio: String
    ) : UserUI {
        override fun bind(
            glide: SetImageWithGlide,
            usernameTextView: AbstractView.Text,
            profileImageView: AbstractView.Image,
            totalCollectionsTextView: AbstractView.Text,
            totalLikesTextView: AbstractView.Text,
            totalPhotosTextView: AbstractView.Text,
            bioTextView: AbstractView.Text
        ) {
            glide.setImage(
                url,
                profileImageView.getImage(),
                needCrop = false,
                needCircleCrop = true
            )
            currentId = username
            usernameTextView.map(username)
            totalCollectionsTextView.map(totalCollections)
            totalLikesTextView.map(totalLikes)
            totalPhotosTextView.map(totalPhotos)
            if (bio.isEmpty()) {
                bioTextView.hide()
            } else {
                bioTextView.show()
                bioTextView.map(bio)
            }
        }

    }
}