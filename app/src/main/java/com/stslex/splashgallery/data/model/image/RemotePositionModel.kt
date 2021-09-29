package com.stslex.splashgallery.data.model.image

import com.google.gson.annotations.SerializedName

data class RemotePositionModel(
    @SerializedName("latitude")
    val latitude: Double?,
    @SerializedName("longitude")
    val longitude: Double?
)
