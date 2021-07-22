package com.stslex.splashgallery.data.model.remote

import com.google.gson.annotations.SerializedName

data class RemoteTopicsModel(
    @SerializedName("id") val id: String,
    @SerializedName("slug") val slug: String,
    @SerializedName("title") val title: String
)