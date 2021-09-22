package com.stslex.splashgallery.core.model.remote

import com.google.gson.annotations.SerializedName
import com.stslex.splashgallery.core.model.PhotoStatistics
import com.stslex.splashgallery.core.model.domain.collection.CollectionModel
import com.stslex.splashgallery.core.model.domain.image.*
import com.stslex.splashgallery.core.model.domain.user.UserModel

class RemoteImageModel(
    @SerializedName("id") val id: String,
    @SerializedName("created_at") val created_at: String?,
    @SerializedName("updated_at") val updated_at: String?,
    @SerializedName("width") val width: Int?,
    @SerializedName("height") val height: Int?,
    @SerializedName("color") val color: String? = "#E0E0E0",
    @SerializedName("blur_hash") val blur_hash: String?,
    @SerializedName("views") val views: Int?,
    @SerializedName("downloads") val downloads: Int?,
    @SerializedName("likes") val likes: Int?,
    @SerializedName("liked_by_user") var liked_by_user: Boolean?,
    @SerializedName("description") val description: String?,
    @SerializedName("alt_description") val alt_description: String?,
    @SerializedName("exif") val exif: ExifModel?,
    @SerializedName("location") val location: LocationModel?,
    @SerializedName("tags") val tags: List<TagModel>?,
    @SerializedName("current_user_collections") val current_user_collections: List<CollectionModel>?,
    @SerializedName("sponsorship") val sponsorship: Sponsorship?,
    @SerializedName("urls") val urls: UrlsModel,
    @SerializedName("links") val links: LinksImageModel?,
    @SerializedName("user") val user: UserModel?,
    @SerializedName("statistics") val statistics: PhotoStatistics?
)