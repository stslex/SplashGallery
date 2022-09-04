package com.stslex.splashgallery.ui.model.image

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class UrlsModel(
    val raw: String,
    val full: String,
    val regular: String,
    val small: String,
    val thumb: String
) : Parcelable