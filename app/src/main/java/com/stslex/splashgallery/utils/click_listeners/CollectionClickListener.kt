package com.stslex.splashgallery.utils.click_listeners

import android.widget.ImageView

class CollectionClickListener(val clickListener: (ImageView, String) -> Unit) {
    fun onClick(
        imageView: ImageView,
        title: String
    ) = clickListener(imageView, title)
}