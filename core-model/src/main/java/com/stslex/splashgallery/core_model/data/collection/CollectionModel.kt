package com.stslex.splashgallery.core_model.data.collection

import android.os.Parcelable
import com.stslex.splashgallery.core_model.data.image.ImageDataModel
import com.stslex.splashgallery.core_model.data.image.TagModel
import com.stslex.splashgallery.core_model.data.user.UserModel
import kotlinx.parcelize.Parcelize

@Parcelize
data class CollectionModel(
    val id: String,
    val title: String,
    val description: String,
    val published_at: String,
    val updated_at: String,
    val curated: Boolean,
    val featured: Boolean,
    val total_photos: Int,
    val private: Boolean,
    val share_key: String,
    val tags: List<TagModel>,
    val cover_photo: ImageDataModel,
    val preview_photos: List<ImageDataModel>,
    val user: UserModel,
    val links: LinksCollectionModel
) : Parcelable