package com.stslex.splashgallery.utils.glide

import android.annotation.SuppressLint
import android.graphics.drawable.Drawable
import android.widget.ImageView
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import java.lang.ref.WeakReference
import javax.inject.Inject

interface ImageSetter {

    fun setImage(
        fragment: WeakReference<Fragment>,
        url: String,
        imageView: ImageView,
        needCrop: Boolean = false,
        needCircleCrop: Boolean = false
    )

    class Base @Inject constructor() : ImageSetter {

        @SuppressLint("CheckResult")
        override fun setImage(
            fragment: WeakReference<Fragment>,
            url: String,
            imageView: ImageView,
            needCrop: Boolean,
            needCircleCrop: Boolean
        ) {
            fragment.get()?.run {
                Glide.with(this).load(url).listener(primaryRequestListener).apply {
                    if (needCrop) centerCrop()
                    if (needCircleCrop) circleCrop()
                    if (!needCircleCrop && !needCrop) fitCenter()
                    disallowHardwareConfig()
                    preload()
                    into(imageView)
                }
            }
        }

        private val Fragment.primaryRequestListener: RequestListener<Drawable>
            get() = object : RequestListener<Drawable> {
                override fun onLoadFailed(
                    e: GlideException?,
                    model: Any?,
                    target: Target<Drawable>?,
                    isFirstResource: Boolean
                ): Boolean {
                    startPostponedEnterTransition()
                    return false
                }

                override fun onResourceReady(
                    resource: Drawable?,
                    model: Any?,
                    target: Target<Drawable>?,
                    dataSource: DataSource?,
                    isFirstResource: Boolean
                ): Boolean {
                    startPostponedEnterTransition()
                    return false
                }
            }
    }
}