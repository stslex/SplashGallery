package com.stslex.splashgallery.utils

import android.annotation.SuppressLint
import android.graphics.drawable.Drawable
import android.widget.ImageView
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.Target
import com.stslex.splashgallery.R

fun ImageView.downloadAndSet(url: String) {
    Glide.with(this)
        .load(url)
        .apply(RequestOptions.placeholderOf(R.drawable.ic_launcher_foreground))
        .into(this)
}

fun ImageView.downloadAndSetSmallRound(url: String) {
    Glide.with(this)
        .load(url)
        .circleCrop()
        .apply(RequestOptions.placeholderOf(R.drawable.ic_launcher_foreground))
        .into(this)
}

@SuppressLint("CheckResult")
fun Fragment.setImageWithRequest(
    url: String,
    imageView: ImageView,
    needCrop: Boolean = false,
    needCircleCrop: Boolean = false
) {
    val glide = Glide.with(this).load(url).listener(primaryRequestListener)
    if (needCrop) glide.centerCrop()
    if (needCircleCrop) glide.circleCrop()
    glide.into(imageView)
}

fun Fragment.setRoundImageWithRequest(url: String, imageView: ImageView) {
    Glide.with(this)
        .load(url)
        .circleCrop()
        .listener(primaryRequestListener)
        .into(imageView)
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