package com.stslex.splashgallery.data.model.image

import com.google.gson.annotations.SerializedName

data class RemoteExifModel(
    @SerializedName("make")
    val make: String?,
    @SerializedName("model")
    val model: String?,
    @SerializedName("exposure_time")
    val exposure_time: String?,
    @SerializedName("aperture")
    val aperture: String?,
    @SerializedName("focal_length")
    val focal_length: String?,
    @SerializedName("iso")
    val iso: Int?
)