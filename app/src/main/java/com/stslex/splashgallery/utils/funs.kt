package com.stslex.splashgallery.utils

import android.content.Context
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.stslex.splashgallery.GalleryApplication
import com.stslex.splashgallery.R
import com.stslex.splashgallery.di.component.AppComponent

val Context.appComponent: AppComponent
    get() = when (this) {
        is GalleryApplication -> appComponent
        else -> this.applicationContext.appComponent
    }

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