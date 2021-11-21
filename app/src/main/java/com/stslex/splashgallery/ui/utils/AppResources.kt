package com.stslex.splashgallery.ui.utils

import android.content.res.Resources
import com.stslex.splashgallery.R

object AppResources {

    private var nullPhotos: String? = null
    val photos: String get() = checkNotNull(nullPhotos)

    private var nullCollections: String? = null
    val collections: String get() = checkNotNull(nullCollections)

    private var nullLikes: String? = null
    val likes: String get() = checkNotNull(nullLikes)

    private var nullUnknown: String? = null
    val unknown: String get() = checkNotNull(nullUnknown)

    fun Resources.setResources() {
        nullPhotos = getString(R.string.label_photos)
        nullLikes = getString(R.string.label_likes)
        nullCollections = getString(R.string.label_collections)
        nullUnknown = getString(R.string.string_unknown)
    }
}
