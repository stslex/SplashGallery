package com.stslex.splashgallery.ui.main_screen_pager.all_photos

import android.widget.ImageView
import com.stslex.splashgallery.data.model.domain.image.ImageModel

class AllPhotosClickListener(val clickListener: (ImageModel, ImageView) -> Unit) {
    fun onClick(
        image: ImageModel,
        imageView: ImageView,
    ) = clickListener(image, imageView)
}