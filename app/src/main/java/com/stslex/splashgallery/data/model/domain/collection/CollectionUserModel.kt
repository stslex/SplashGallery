package com.stslex.splashgallery.data.model.domain.collection

import com.stslex.splashgallery.data.model.domain.image.ProfileImageModel
import com.stslex.splashgallery.data.model.domain.image.UserLinksModel

data class CollectionUserModel(
    val id: String,
    val updated_at: String,
    val username: String,
    val name: String,
    val portfolio_url: String,
    val bio: String,
    val location: String,
    val total_likes: String,
    val total_photos: String,
    val total_collections: String,
    val profile_image: ProfileImageModel,
    val links: UserLinksModel
)