package com.stslex.splashgallery.ui.model.user

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ProfileImageModel(
    val small: String,
    val medium: String,
    val large: String
) : Parcelable
