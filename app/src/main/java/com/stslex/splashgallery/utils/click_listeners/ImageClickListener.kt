package com.stslex.splashgallery.utils.click_listeners

import android.widget.ImageView
import com.stslex.splashgallery.data.model.domain.image.ImageModel

class ImageClickListener(val clickListener: (ImageModel, ImageView) -> Unit) {
    fun onClick(
        image: ImageModel,
        imageView: ImageView,
    ) = clickListener(image, imageView)
}