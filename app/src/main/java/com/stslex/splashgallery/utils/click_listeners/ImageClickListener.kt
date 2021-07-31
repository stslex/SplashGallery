package com.stslex.splashgallery.utils.click_listeners

import android.widget.ImageView
import android.widget.LinearLayout

class ImageClickListener(
    val imageClickListener: (ImageView, String) -> Unit,
    val userClickListener: (LinearLayout) -> Unit
) {
    fun onImageClick(
        imageView: ImageView,
        id: String
    ) = imageClickListener(imageView, id)

    fun onUserCLick(
        user: LinearLayout
    ) = userClickListener(user)
}