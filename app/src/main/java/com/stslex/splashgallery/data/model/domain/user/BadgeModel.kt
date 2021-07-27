package com.stslex.splashgallery.data.model.domain.user

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class BadgeModel(
    val title: String?,
    val primary: Boolean?,
    val slug: String?,
    val link: String?
) : Parcelable
