package com.stslex.splashgallery.ui.photos.models

import android.widget.ImageView
import android.widget.TextView
import com.google.android.material.card.MaterialCardView
import com.stslex.splashgallery.ui.utils.SetImageWithGlide

interface ImageUI {

    fun bindImage(glide: SetImageWithGlide, imageView: ImageView, cardView: MaterialCardView)
    fun bindUser(
        glide: SetImageWithGlide,
        imageView: ImageView,
        textView: TextView,
        cardView: MaterialCardView
    )

    fun hideUserHead(cardView: MaterialCardView)
    fun getUrl(): String
    fun getId(): String
    fun same(item: ImageUI): Boolean

    data class Base(
        private val id: String,
        private val url: String,
        private val user: UserUI
    ) : ImageUI {

        override fun bindImage(
            glide: SetImageWithGlide,
            imageView: ImageView,
            cardView: MaterialCardView
        ) {
            glide.setImage(url, imageView, needCrop = true)
            cardView.transitionName = id
        }

        override fun bindUser(
            glide: SetImageWithGlide,
            imageView: ImageView,
            textView: TextView,
            cardView: MaterialCardView
        ) {
            user.bindUser(glide, imageView, textView, cardView)
        }

        override fun hideUserHead(cardView: MaterialCardView) {
            user.hideUserHead(cardView)
        }

        override fun getUrl(): String = url
        override fun getId(): String = id

        override fun same(item: ImageUI): Boolean =
            item == this && item.getId() == id
    }
}