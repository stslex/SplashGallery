package com.stslex.splashgallery.data.model.download

import com.google.gson.annotations.SerializedName

data class RemoteDownloadModel(
    @SerializedName("url")
    val url: String
)