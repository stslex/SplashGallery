package com.stslex.splashgallery.data.model.domain.image

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class LocationModel(
    val city: String?,
    val country: String?,
    val position: PositionModel?
) : Parcelable
