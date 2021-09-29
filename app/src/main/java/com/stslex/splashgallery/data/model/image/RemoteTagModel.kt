package com.stslex.splashgallery.data.model.image

import com.google.gson.annotations.SerializedName

data class RemoteTagModel(
    @SerializedName("type")
    val type: String?,
    @SerializedName("title")
    val title: String?
)
