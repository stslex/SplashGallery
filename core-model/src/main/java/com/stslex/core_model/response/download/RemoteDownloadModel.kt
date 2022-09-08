package com.stslex.core_model.response.download

import com.google.gson.annotations.SerializedName

data class RemoteDownloadModel(
    @SerializedName("url")
    val url: String
)