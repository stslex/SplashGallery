package com.stslex.splashgallery.data.model.remote.download

import com.google.gson.annotations.SerializedName

data class RemoteDownloadModel(
    @SerializedName("url")
    val url: String
)