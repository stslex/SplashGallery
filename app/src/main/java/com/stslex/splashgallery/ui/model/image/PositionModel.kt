package com.stslex.splashgallery.ui.model.image

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class PositionModel(
    val latitude: Double,
    val longitude: Double
) : Parcelable
