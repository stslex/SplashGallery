package com.stslex.splashgallery.ui.photos.models

import android.view.View
import com.stslex.splashgallery.ui.core.CustomCardView
import com.stslex.splashgallery.ui.core.CustomImageView
import com.stslex.splashgallery.ui.core.CustomTextView
import com.stslex.splashgallery.ui.core.OnClickListener
import com.stslex.splashgallery.ui.utils.SetImageWithGlide

interface ImageUI {

    fun getUrl(): String
    fun getId(): String
    fun same(item: ImageUI): Boolean
    fun bind(
        isUser: Boolean,
        clickListener: OnClickListener,
        glide: SetImageWithGlide,
        imageView: CustomImageView,
        imageCardView: CustomCardView,
        userImageView: CustomImageView,
        userTextView: CustomTextView,
        userCardView: CustomCardView
    )

    data class Base(
        private val id: String,
        private val url: String,
        private val user: UserUI
    ) : ImageUI {

        override fun bind(
            isUser: Boolean,
            clickListener: OnClickListener,
            glide: SetImageWithGlide,
            imageView: CustomImageView,
            imageCardView: CustomCardView,
            userImageView: CustomImageView,
            userTextView: CustomTextView,
            userCardView: CustomCardView
        ) {
            if (isUser) userCardView.hide()
            else user.bindUser(
                glide,
                clickListener,
                imageView,
                userTextView,
                userCardView
            )
            glide.setImage(url, imageView, needCrop = true)
            imageCardView.transitionName = id
            imageCardView.setOnClickListener(clickListener.imageClick(url))
        }

        override fun getUrl(): String = url
        override fun getId(): String = id
        override fun same(item: ImageUI): Boolean = item == this && item.getId() == id

        private fun OnClickListener.imageClick(url: String) = View.OnClickListener { view ->
            clickImage(view, url)
        }
    }
}