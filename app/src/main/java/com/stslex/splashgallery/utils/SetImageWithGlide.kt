package com.stslex.splashgallery.utils

import android.widget.ImageView

class SetImageWithGlide(
    val makeGlideImage: (
        url: String,
        imageView: ImageView,
        needCrop: Boolean,
        needCircleCrop: Boolean
    ) -> Unit
) {

    fun setImage(
        url: String,
        imageView: ImageView,
        needCrop: Boolean,
        needCircleCrop: Boolean
    ) = makeGlideImage(url, imageView, needCrop, needCircleCrop)

}