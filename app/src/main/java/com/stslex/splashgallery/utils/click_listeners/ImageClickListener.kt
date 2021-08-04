package com.stslex.splashgallery.utils.click_listeners

import android.widget.ImageView
import android.widget.TextView

class ImageClickListener(
    val imageClickListener: (ImageView, String) -> Unit,
    val userClickListener: (TextView) -> Unit
) {
    fun onImageClick(
        imageView: ImageView,
        id: String
    ) = imageClickListener(imageView, id)

    fun onUserCLick(
        user: TextView
    ) = userClickListener(user)
}