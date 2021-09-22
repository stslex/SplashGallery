package com.stslex.splashgallery.core.model.domain.collection

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class LinksCollectionModel(
    val self: String,
    val html: String,
    val photos: String
) : Parcelable