package com.stslex.splashgallery.data.model.image

import com.google.gson.annotations.SerializedName

data class RemoteLocationModel(
    @SerializedName("city")
    val city: String?,
    @SerializedName("country")
    val country: String?,
    @SerializedName("position")
    val position: RemotePositionModel?
)
