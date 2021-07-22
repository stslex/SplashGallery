package com.stslex.splashgallery.data.model.domain.collection

import com.stslex.splashgallery.data.model.domain.image.ImageLinksModel
import com.stslex.splashgallery.data.model.domain.image.ImageUrlsModel

data class CoverPhotoModel(
    val id: String,
    val width: String,
    val height: String,
    val color: String,
    val blur_hash: String,
    val likes: String,
    val liked_by_user: String,
    val description: String,
    val user: CollectionUserModel,
    val urls: ImageUrlsModel,
    val links: ImageLinksModel,
)
