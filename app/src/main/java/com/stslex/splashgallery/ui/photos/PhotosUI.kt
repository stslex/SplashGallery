package com.stslex.splashgallery.ui.photos

import com.stslex.splashgallery.ui.core.AbstractView
import com.stslex.splashgallery.ui.core.CustomCardView
import com.stslex.splashgallery.ui.core.CustomImageView

interface PhotosUI {

    fun bindPhotos(
        image: AbstractView.Image,
        avatar: AbstractView.Image,
        username: AbstractView.Text,
        imageCardView: AbstractView.Card,
        userCardView: AbstractView.Card
    )

    fun bindDetailPhoto(
        image: AbstractView.Image,
        avatar: AbstractView.Image,
        username: AbstractView.Text,
        userCardView: AbstractView.Card,
        photoAperture: AbstractView.Text,
        photoCamera: AbstractView.Text,
        photoDimension: AbstractView.Text,
        photoFocal: AbstractView.Text
    )

    fun openDetailImage(function: (CustomCardView) -> Unit)
    fun openDetailUser(function: (CustomCardView) -> Unit)
    fun same(data: PhotosUI): Boolean
    fun downloadPhoto(function: (String) -> Unit)
    fun imageClick(function: (CustomImageView) -> Unit)

    data class Base(
        private val imageId: String,
        private val imageUrl: String,
        private val userId: String,
        private val userName: String,
        private val userUrl: String,
        private val exif: ExifUI
    ) : PhotosUI {

        data class ExifUI(
            val make: String,
            val model: String,
            val exposure_time: String,
            val aperture: String,
            val focal_length: String,
            val iso: String
        )

        private var _imageCardView: CustomCardView? = null
        private val imageCardView get() = _imageCardView!!

        private var _userCardView: CustomCardView? = null
        private val userCardView get() = _userCardView!!

        private var _imageView: CustomImageView? = null
        private val imageView get() = _imageView!!

        override fun bindPhotos(
            image: AbstractView.Image,
            avatar: AbstractView.Image,
            username: AbstractView.Text,
            imageCardView: AbstractView.Card,
            userCardView: AbstractView.Card
        ) {
            image.load(imageUrl, needCrop = true)
            avatar.load(userId, needCircle = true)
            username.map(userName)
            imageCardView.transit(userId)
            _imageCardView = imageCardView.getCard()
            userCardView.transit(userName)
            _userCardView = userCardView.getCard()
        }

        override fun bindDetailPhoto(
            image: AbstractView.Image,
            avatar: AbstractView.Image,
            username: AbstractView.Text,
            userCardView: AbstractView.Card,
            photoAperture: AbstractView.Text,
            photoCamera: AbstractView.Text,
            photoDimension: AbstractView.Text,
            photoFocal: AbstractView.Text
        ) {
            image.load(imageUrl, needCrop = true)
            _imageView = image.getImage()
            avatar.load(userId, needCircle = true)
            username.map(userName)
            userCardView.transit(userName)
            _userCardView = userCardView.getCard()
            photoAperture.map(exif.aperture)
            photoCamera.map(exif.make)
            photoDimension.map(exif.exposure_time)
            photoFocal.map(exif.focal_length)
        }

        override fun openDetailImage(function: (CustomCardView) -> Unit) = function(imageCardView)
        override fun openDetailUser(function: (CustomCardView) -> Unit) = function(userCardView)
        override fun downloadPhoto(function: (String) -> Unit) = function(imageId)
        override fun imageClick(function: (CustomImageView) -> Unit) = function(imageView)

        override fun same(data: PhotosUI): Boolean =
            data is Base && data.imageId == imageId

    }
}