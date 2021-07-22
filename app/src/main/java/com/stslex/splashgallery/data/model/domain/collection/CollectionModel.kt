package com.stslex.splashgallery.data.model.domain.collection

import com.stslex.splashgallery.data.model.domain.image.ImageUrlsModel

data class CollectionModel(
    val id: String,
    val title: String,
    val description: String,
    val published_at: String,
    val last_collected_at: String,
    val updated_at: String,
    val total_photos: String,
    val private: String,
    val share_key: String,
    val cover_photo: CoverPhotoModel,
    val user: CollectionUserModel,
    val links: ImageUrlsModel,
)