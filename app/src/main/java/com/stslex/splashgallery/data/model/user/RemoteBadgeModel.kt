package com.stslex.splashgallery.data.model.user

import com.google.gson.annotations.SerializedName

data class RemoteBadgeModel(
    @SerializedName("title")
    val title: String?,
    @SerializedName("primary")
    val primary: Boolean?,
    @SerializedName("slug")
    val slug: String?,
    @SerializedName("link")
    val link: String?
)
