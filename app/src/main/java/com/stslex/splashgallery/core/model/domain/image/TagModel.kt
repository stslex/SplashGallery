package com.stslex.splashgallery.core.model.domain.image

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class TagModel(
    val type: String?,
    val title: String?
) : Parcelable
