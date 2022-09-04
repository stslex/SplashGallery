package com.stslex.splashgallery.data.model.data.topics

import com.stslex.splashgallery.data.model.data.collection.LinksCollectionModel
import com.stslex.splashgallery.data.model.data.image.ImageModel
import com.stslex.splashgallery.data.model.data.user.UserModel

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
    val cover_photo: ImageModel,
    val preview_photos: List<PreviewPhotosModel>
)