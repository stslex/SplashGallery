package com.stslex.splashgallery.data.model.data.user

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ProfileImageModel(
    val small: String,
    val medium: String,
    val large: String
) : Parcelable
