package com.stslex.splashgallery.data.model.remote

import com.stslex.splashgallery.data.model.domain.collection.LinksCollectionModel
import com.stslex.splashgallery.data.model.domain.image.ImageModel
import com.stslex.splashgallery.data.model.domain.image.TagModel
import com.stslex.splashgallery.data.model.domain.user.UserModel

data class RemoteCollectionModel(
    val id: String,
    val title: String,
    val description: String?,
    val published_at: String?,
    val updated_at: String?,
    val curated: Boolean?,
    val featured: Boolean?,
    val total_photos: Int,
    val private: Boolean?,
    val share_key: String?,
    val tags: List<TagModel>?,
    val cover_photo: ImageModel?,
    val preview_photos: List<ImageModel>?,
    val user: UserModel?,
    val links: LinksCollectionModel?
)
