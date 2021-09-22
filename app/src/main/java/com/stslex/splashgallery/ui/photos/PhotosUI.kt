package com.stslex.splashgallery.ui.photos

import com.stslex.splashgallery.ui.core.AbstractView
import com.stslex.splashgallery.ui.core.CustomCardView
import com.stslex.splashgallery.ui.core.CustomImageView
import com.stslex.splashgallery.utils.SetImageWithGlide

interface PhotosUI {

    fun bindPhotos(
        glide: SetImageWithGlide,
        image: AbstractView.Image,
        avatar: AbstractView.Image,
        username: AbstractView.Text,
        imageCardView: AbstractView.Card,
        userCardView: AbstractView.Card
    )

    fun bindDetailPhoto(
        glide: SetImageWithGlide,
        image: AbstractView.Image,
        avatar: AbstractView.Image,
        username: AbstractView.Text,
        userCardView: AbstractView.Card,
        photoAperture: AbstractView.Text,
        photoCamera: AbstractView.Text,
        photoDimension: AbstractView.Text,
        photoFocal: AbstractView.Text
    )

    fun openImage(function: (CustomCardView, String) -> Unit)
    fun openUser(function: (CustomCardView) -> Unit)
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
            glide: SetImageWithGlide,
            image: AbstractView.Image,
            avatar: AbstractView.Image,
            username: AbstractView.Text,
            imageCardView: AbstractView.Card,
            userCardView: AbstractView.Card
        ) {
            glide.makeGlideImage(imageUrl, image.getImage(), true, false)
            glide.makeGlideImage(userUrl, avatar.getImage(), true, true)
            username.map(userName)
            _imageCardView = imageCardView.getCardAndSetTransitionName(imageId)
            _userCardView = userCardView.getCardAndSetTransitionName(userName)
        }

        override fun bindDetailPhoto(
            glide: SetImageWithGlide,
            image: AbstractView.Image,
            avatar: AbstractView.Image,
            username: AbstractView.Text,
            userCardView: AbstractView.Card,
            photoAperture: AbstractView.Text,
            photoCamera: AbstractView.Text,
            photoDimension: AbstractView.Text,
            photoFocal: AbstractView.Text
        ) {
            _imageView = image.getImageAndSetTransitionName(imageId)
            glide.makeGlideImage(userUrl, avatar.getImage(), true, true)
            username.map(userName)
            _userCardView = userCardView.getCardAndSetTransitionName(userName)
            photoAperture.map(exif.aperture)
            photoCamera.map(exif.make)
            photoDimension.map(exif.exposure_time)
            photoFocal.map(exif.focal_length)
        }

        override fun openImage(function: (CustomCardView, String) -> Unit) =
            function(imageCardView, imageUrl)

        override fun openUser(function: (CustomCardView) -> Unit) = function(userCardView)
        override fun downloadPhoto(function: (String) -> Unit) = function(imageId)
        override fun imageClick(function: (CustomImageView) -> Unit) = function(imageView)

        override fun same(data: PhotosUI): Boolean =
            data is Base && data.imageId == imageId

    }
}