package com.stslex.splashgallery.core_ui

import android.widget.ImageView
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject

class SetImageWithGlide @AssistedInject constructor(
    @Assisted("makeImage") val create: (
        url: String,
        imageView: ImageView,
        needCrop: Boolean,
        needCircleCrop: Boolean
    ) -> Unit
) {

    fun setImage(
        url: String,
        imageView: ImageView,
        needCrop: Boolean = false,
        needCircleCrop: Boolean = false
    ) = create(url, imageView, needCrop, needCircleCrop)

    @AssistedFactory
    interface Factory {
        fun create(
            @Assisted("makeImage") create: (
                url: String,
                imageView: ImageView,
                needCrop: Boolean,
                needCircleCrop: Boolean
            ) -> Unit
        ): SetImageWithGlide
    }
}