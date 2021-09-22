package com.stslex.splashgallery.core.model.domain.user

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class BadgeModel(
    val title: String?,
    val primary: Boolean?,
    val slug: String?,
    val link: String?
) : Parcelable
