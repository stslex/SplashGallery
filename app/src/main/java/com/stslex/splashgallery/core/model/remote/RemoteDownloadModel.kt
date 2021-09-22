package com.stslex.splashgallery.core.model.remote

import com.google.gson.annotations.SerializedName

data class RemoteDownloadModel(
    @SerializedName("url")
    val url: String
)