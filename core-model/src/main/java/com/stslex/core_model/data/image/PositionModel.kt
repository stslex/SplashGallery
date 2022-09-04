package com.stslex.core_model.data.image

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class PositionModel(
    val latitude: Double,
    val longitude: Double
) : Parcelable
