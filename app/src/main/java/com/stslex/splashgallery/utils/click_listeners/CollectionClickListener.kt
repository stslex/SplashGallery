package com.stslex.splashgallery.utils.click_listeners

import android.widget.ImageView
import android.widget.LinearLayout

class CollectionClickListener(
    val onImageClickListener: (ImageView, String) -> Unit,
    val onUserClickListener: (LinearLayout) -> Unit
) {
    fun onImageClick(
        imageView: ImageView,
        title: String
    ) = onImageClickListener(imageView, title)

    fun onUserClick(
        user: LinearLayout
    ) = onUserClickListener(user)
}