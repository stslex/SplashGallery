package com.stslex.splashgallery.data.model.user

import com.google.gson.annotations.SerializedName

data class RemoteProfileImageModel(
    @SerializedName("small")
    val small: String,
    @SerializedName("medium")
    val medium: String,
    @SerializedName("large")
    val large: String
)
