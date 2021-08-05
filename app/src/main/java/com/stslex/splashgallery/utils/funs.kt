package com.stslex.splashgallery.utils

import android.app.Activity
import android.content.Context
import com.stslex.splashgallery.GalleryApplication
import com.stslex.splashgallery.R
import com.stslex.splashgallery.di.component.AppComponent

val Context.appComponent: AppComponent
    get() = when (this) {
        is GalleryApplication -> appComponent
        else -> this.applicationContext.appComponent
    }

fun Activity.setResources() {
    photos = getString(R.string.label_photos)
    likes = getString(R.string.label_likes)
    collections = getString(R.string.label_collections)
    cache = cacheDir
}