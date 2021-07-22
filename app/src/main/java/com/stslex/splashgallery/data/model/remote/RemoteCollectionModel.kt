package com.stslex.splashgallery.data.model.remote

import com.google.gson.annotations.SerializedName
import com.stslex.splashgallery.data.model.domain.collection.CollectionUserModel
import com.stslex.splashgallery.data.model.domain.collection.CoverPhotoModel
import com.stslex.splashgallery.data.model.domain.image.ImageUrlsModel

data class RemoteCollectionModel(
    @SerializedName("id") val id: String,
    @SerializedName("title") val title: String,
    @SerializedName("description") val description: String,
    @SerializedName("published_at") val published_at: String,
    @SerializedName("last_collected_at") val last_collected_at: String,
    @SerializedName("updated_at") val updated_at: String,
    @SerializedName("total_photos") val total_photos: String,
    @SerializedName("private") val private: String,
    @SerializedName("share_key") val share_key: String,
    @SerializedName("cover_photo") val cover_photo: CoverPhotoModel,
    @SerializedName("user") val user: CollectionUserModel,
    @SerializedName("links") val links: ImageUrlsModel,
)
