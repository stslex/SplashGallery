package com.stslex.splashgallery.utils.click_listeners

import android.widget.ImageView

class ImageClickListener(val clickListener: (ImageView, String) -> Unit) {
    fun onClick(
        imageView: ImageView,
        id: String
    ) = clickListener(imageView, id)
}