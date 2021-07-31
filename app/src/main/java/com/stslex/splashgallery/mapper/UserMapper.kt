package com.stslex.splashgallery.mapper

import com.stslex.splashgallery.data.model.domain.user.UserModel
import com.stslex.splashgallery.data.model.remote.RemoteUserModel

class UserMapper : BaseMapper<RemoteUserModel, UserModel> {
    override fun transformToDomain(type: RemoteUserModel): UserModel = UserModel(
        id = type.id,
        updated_at = type.updated_at,
        username = type.username,
        name = type.name,
        first_name = type.first_name,
        last_name = type.last_name,
        instagram_username = type.instagram_username,
        twitter_username = type.twitter_username,
        portfolio_url = type.portfolio_url,
        bio = type.bio,
        location = type.location,
        total_likes = type.total_likes,
        total_photos = type.total_photos,
        total_collections = type.total_collections,
        followed_by_user = type.followed_by_user,
        followers_count = type.followers_count,
        following_count = type.following_count,
        downloads = type.downloads,
        profile_image = type.profile_image,
        badge = type.badge,
        links = type.links,
        photos = type.photos
    )
}