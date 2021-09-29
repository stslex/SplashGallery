package com.stslex.splashgallery.ui.model.user

import android.os.Parcelable
import com.stslex.splashgallery.ui.model.image.ImageModel
import kotlinx.parcelize.Parcelize

@Parcelize
data class UserModel(
    val id: String,
    val updated_at: String,
    val username: String,
    val name: String,
    val first_name: String,
    val last_name: String,
    val instagram_username: String,
    val twitter_username: String,
    val portfolio_url: String,
    val bio: String,
    val location: String,
    val total_likes: Int,
    val total_photos: Int,
    val total_collections: Int,
    val followed_by_user: Boolean,
    val followers_count: Int,
    val following_count: Int,
    val downloads: Int,
    val profile_image: ProfileImageModel?,
    val badge: BadgeModel?,
    val links: UserLinksModel?,
    val photos: List<ImageModel>?
) : Parcelable
