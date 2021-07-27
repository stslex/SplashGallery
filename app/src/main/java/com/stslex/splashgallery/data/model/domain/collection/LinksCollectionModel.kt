package com.stslex.splashgallery.data.model.domain.collection

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class LinksCollectionModel(
    val self: String,
    val html: String,
    val photos: String
) : Parcelable