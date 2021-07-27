package com.stslex.splashgallery.data.model.domain.user

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class UserLinksModel(
    val self: String,
    val html: String,
    val photos: String,
    val likes: String,
    val portfolio: String,
    val following: String,
    val followers: String
) : Parcelable
