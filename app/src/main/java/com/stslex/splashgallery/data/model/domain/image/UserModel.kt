package com.stslex.splashgallery.data.model.domain.image

data class UserModel(
    val id: String,
    val username: String,
    val name: String,
    val portfolio_url: String,
    val bio: String,
    val location: String,
    val total_likes: String,
    val total_photos: String,
    val total_collections: String,
    val instagram_username: String,
    val twitter_username: String,
    val profile_image: ProfileImageModel,
    val links: UserLinksModel
)
