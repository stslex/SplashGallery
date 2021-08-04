package com.stslex.splashgallery.utils.click_listeners

import android.widget.ImageView
import android.widget.TextView

class CollectionClickListener(
    val onImageClickListener: (ImageView, String) -> Unit,
    val onUserClickListener: (TextView) -> Unit
) {
    fun onImageClick(
        imageView: ImageView,
        title: String
    ) = onImageClickListener(imageView, title)

    fun onUserClick(
        user: TextView
    ) = onUserClickListener(user)
}