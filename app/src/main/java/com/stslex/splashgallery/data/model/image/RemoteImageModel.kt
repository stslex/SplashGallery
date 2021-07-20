package com.stslex.splashgallery.data.model.image

import com.stslex.splashgallery.data.model.image.CurrentUserCollectionModel
import com.stslex.splashgallery.data.model.image.ImageLinksModel
import com.stslex.splashgallery.data.model.image.ImageUrlsModel
import com.stslex.splashgallery.data.model.image.UserModel

class RemoteImageModel(
    val id: String,
    val created_at: String,
    val updated_at: String,
    val width: String,
    val height: String,
    val color: String,
    val blur_hash: String,
    val likes: String,
    val liked_by_user: Boolean,
    val description: String,
    val user: UserModel,
    val current_user_collection: List<CurrentUserCollectionModel>,
    val urls: ImageUrlsModel,
    val links: ImageLinksModel
)