package com.stslex.splashgallery.data.model.domain.image

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class PositionModel(
    val latitude: Double?,
    val longitude: Double?
) : Parcelable
