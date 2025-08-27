package com.stslex.splashgallery.core_ui

import android.annotation.SuppressLint
import android.graphics.drawable.Drawable
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestListener
import javax.inject.Inject

interface ImageSetter {

    fun setImage(
        url: String,
        imageView: ImageView,
        needCrop: Boolean = false,
        needCircleCrop: Boolean = false,
        requestListener: RequestListener<Drawable>
    )

    class Base @Inject constructor() : ImageSetter {

        @SuppressLint("CheckResult")
        override fun setImage(
            url: String,
            imageView: ImageView,
            needCrop: Boolean,
            needCircleCrop: Boolean,
            requestListener: RequestListener<Drawable>
        ) {
            Glide.with(imageView).load(url).listener(requestListener).apply {
                if (needCrop) centerCrop()
                if (needCircleCrop) circleCrop()
                if (!needCircleCrop && !needCrop) fitCenter()
                disallowHardwareConfig()
                preload()
                into(imageView)
            }
        }
    }
}