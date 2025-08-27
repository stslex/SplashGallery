package com.stslex.splashgallery.core_model.data.topics

import com.stslex.splashgallery.core_model.data.collection.LinksCollectionModel
import com.stslex.splashgallery.core_model.data.image.ImageDataModel
import com.stslex.splashgallery.core_model.data.user.UserModel

data class TopicsModel(
    val id: String,
    val slug: String,
    val title: String,
    val description: String,
    val published_at: String,
    val updated_at: String,
    val starts_at: String,
    val ends_at: String,
    val only_submissions_after: String,
    val featured: String,
    val total_photos: String,
    val links: LinksCollectionModel,
    val status: String,
    val owners: List<UserModel>,
    val cover_photo: ImageDataModel,
    val preview_photos: List<PreviewPhotosModel>
)