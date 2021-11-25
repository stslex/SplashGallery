package com.stslex.splashgallery.ui.photos

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.google.android.material.card.MaterialCardView
import com.stslex.splashgallery.ui.utils.SetImageWithGlide

interface UserUI {

    fun bindUser(
        glide: SetImageWithGlide,
        imageView: ImageView,
        textView: TextView,
        cardView: MaterialCardView
    )

    fun hideUserHead(cardView: MaterialCardView)

    data class Base(
        private val username: String,
        private val url: String
    ) : UserUI {

        override fun bindUser(
            glide: SetImageWithGlide,
            imageView: ImageView,
            textView: TextView,
            cardView: MaterialCardView
        ) {
            cardView.visibility = View.VISIBLE
            glide.setImage(url, imageView, needCrop = true, needCircleCrop = true)
            textView.text = username
            cardView.transitionName = username
        }

        override fun hideUserHead(cardView: MaterialCardView) {
            cardView.visibility = View.GONE
        }
    }
}