package com.stslex.splashgallery.core.model.domain.image

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ExifModel(
    val make: String?,
    val model: String?,
    val exposure_time: String?,
    val aperture: String?,
    val focal_length: String?,
    val iso: Int?
) : Parcelable