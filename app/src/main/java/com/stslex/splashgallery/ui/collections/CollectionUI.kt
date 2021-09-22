package com.stslex.splashgallery.ui.collections

import com.stslex.splashgallery.ui.core.AbstractView
import com.stslex.splashgallery.ui.core.CustomCardView
import com.stslex.splashgallery.utils.SetImageWithGlide

interface CollectionUI {

    fun bindCollections(
        glide: SetImageWithGlide,
        collectionTitle: AbstractView.Text,
        collectionImage: AbstractView.Image,
        collectionNum: AbstractView.Text,
        usernameTextView: AbstractView.Text,
        userImage: AbstractView.Image,
        userCardView: AbstractView.Card,
        imageCardView: AbstractView.Card
    )

    fun openImage(function: (CustomCardView) -> Unit)
    fun openUser(function: (CustomCardView) -> Unit)

    data class Base(
        private val id: String,
        private val title: String,
        private val url: String,
        private val number: String,
        private val username: String,
        private val avatar: String
    ) : CollectionUI {

        private var _userCard: CustomCardView? = null
        private val userCard get() = _userCard!!

        private var _imageCard: CustomCardView? = null
        private val imageCard get() = _imageCard!!

        override fun bindCollections(
            glide: SetImageWithGlide,
            collectionTitle: AbstractView.Text,
            collectionImage: AbstractView.Image,
            collectionNum: AbstractView.Text,
            usernameTextView: AbstractView.Text,
            userImage: AbstractView.Image,
            userCardView: AbstractView.Card,
            imageCardView: AbstractView.Card
        ) {
            collectionTitle.map(title)
            collectionNum.map(number)
            usernameTextView.map(username)
            glide.setImage(url, collectionImage.getImage(), needCrop = true, needCircleCrop = false)
            glide.setImage(avatar, userImage.getImage(), needCrop = false, needCircleCrop = true)
            _imageCard = imageCardView.getCardAndSetTransitionName(id)
            _userCard = userCardView.getCardAndSetTransitionName(username)
        }

        override fun openImage(function: (CustomCardView) -> Unit) = function(imageCard)
        override fun openUser(function: (CustomCardView) -> Unit) = function(userCard)

    }
}