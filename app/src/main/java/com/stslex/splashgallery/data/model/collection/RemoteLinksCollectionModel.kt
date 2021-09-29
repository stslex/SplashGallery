package com.stslex.splashgallery.data.model.collection

import com.google.gson.annotations.SerializedName

data class RemoteLinksCollectionModel(
    @SerializedName("self")
    val self: String,
    @SerializedName("html")
    val html: String,
    @SerializedName("photos")
    val photos: String
)