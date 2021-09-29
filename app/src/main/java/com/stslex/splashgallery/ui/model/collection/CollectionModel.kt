package com.stslex.splashgallery.ui.model.collection

import android.os.Parcelable
import com.stslex.splashgallery.ui.model.image.ImageModel
import com.stslex.splashgallery.ui.model.image.TagModel
import com.stslex.splashgallery.ui.model.user.UserModel
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
    val tags: List<TagModel>?,
    val cover_photo: ImageModel?,
    val preview_photos: List<ImageModel>?,
    val user: UserModel?,
    val links: LinksCollectionModel?
) : Parcelable