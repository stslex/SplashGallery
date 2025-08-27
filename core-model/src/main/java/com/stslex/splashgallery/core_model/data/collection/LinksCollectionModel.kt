package com.stslex.splashgallery.core_model.data.collection

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class LinksCollectionModel(
    val self: String,
    val html: String,
    val photos: String
) : Parcelable