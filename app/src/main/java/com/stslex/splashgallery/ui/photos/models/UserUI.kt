package com.stslex.splashgallery.ui.photos.models

import android.view.View
import android.widget.ImageView
import com.stslex.splashgallery.ui.core.CustomCardView
import com.stslex.splashgallery.ui.core.CustomTextView
import com.stslex.splashgallery.ui.core.OnClickListener
import com.stslex.core_ui.SetImageWithGlide

interface UserUI {

    fun bindUser(
        glide: SetImageWithGlide,
        clickListener: OnClickListener,
        imageView: ImageView,
        textView: CustomTextView,
        cardView: CustomCardView
    )

    data class Base(
        private val username: String,
        private val url: String
    ) : UserUI {

        override fun bindUser(
            glide: SetImageWithGlide,
            clickListener: OnClickListener,
            imageView: ImageView,
            textView: CustomTextView,
            cardView: CustomCardView
        ) {
            cardView.show()
            glide.setImage(
                url = url,
                imageView = imageView,
                needCrop = true,
                needCircleCrop = true
            )
            textView.map(username)
            cardView.transitionName = username
            cardView.setOnClickListener(clickListener.userClick)
        }

        private val OnClickListener.userClick
            get() = View.OnClickListener(::clickUser)
    }
}