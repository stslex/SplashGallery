package com.stslex.splashgallery.ui.model.image

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class LinksImageModel(
    val self: String,
    val html: String,
    val download: String,
    val download_location: String
) : Parcelable