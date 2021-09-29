package com.stslex.splashgallery.data.model.remote

import com.google.gson.annotations.SerializedName
import com.stslex.splashgallery.data.model.domain.collection.LinksCollectionModel
import com.stslex.splashgallery.data.model.domain.image.ImageModel
import com.stslex.splashgallery.data.model.domain.image.TagModel
import com.stslex.splashgallery.data.model.domain.user.UserModel

data class RemoteCollectionModel(
    @SerializedName("id") val id: String,
    @SerializedName("title") val title: String,
    @SerializedName("description") val description: String?,
    @SerializedName("published_at") val published_at: String?,
    @SerializedName("updated_at") val updated_at: String?,
    @SerializedName("curated") val curated: Boolean?,
    @SerializedName("featured") val featured: Boolean?,
    @SerializedName("total_photos") val total_photos: Int,
    @SerializedName("private") val private: Boolean?,
    @SerializedName("share_key") val share_key: String?,
    @SerializedName("tags") val tags: List<TagModel>?,
    @SerializedName("cover_photo") val cover_photo: ImageModel?,
    @SerializedName("preview_photos") val preview_photos: List<ImageModel>?,
    @SerializedName("user") val user: UserModel?,
    @SerializedName("links") val links: LinksCollectionModel?
)
