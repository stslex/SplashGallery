package com.stslex.splashgallery.data.model

data class ImageModel(
    val id: String,
    val created_at: String,
    val updated_at: String,
    val width: String,
    val height: String,
    val color: String,
    val blur_hash: String,
    val likes: String,
    val liked_by_user: Boolean,
    val user: UserModel,
    val urls: ImageUrlsModel,
)

