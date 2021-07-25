package com.stslex.splashgallery.data.model.remote

import com.stslex.splashgallery.data.model.domain.collection.CollectionModel
import com.stslex.splashgallery.data.model.domain.image.*
import com.stslex.splashgallery.data.model.domain.user.UserModel
import com.stslex.splashgallery.data.model.newmodel.PhotoStatistics

class RemoteImageModel(
    val id: String,
    val created_at: String?,
    val updated_at: String?,
    val width: Int?,
    val height: Int?,
    val color: String? = "#E0E0E0",
    val blur_hash: String?,
    val views: Int?,
    val downloads: Int?,
    val likes: Int?,
    var liked_by_user: Boolean?,
    val description: String?,
    val alt_description: String?,
    val exif: ExifModel?,
    val location: LocationModel?,
    val tags: List<TagModel>?,
    val current_user_collections: List<CollectionModel>?,
    val sponsorship: Sponsorship?,
    val urls: UrlsModel,
    val links: LinksImageModel?,
    val user: UserModel?,
    val statistics: PhotoStatistics?
)