package com.stslex.splashgallery.mapper

import com.stslex.splashgallery.data.model.domain.image.ImageModel
import com.stslex.splashgallery.data.model.remote.RemoteImageModel

class ImageMapper : BaseMapper<RemoteImageModel, ImageModel> {
    override fun transformToDomain(type: RemoteImageModel): ImageModel = ImageModel(
        id = type.id,
        created_at = type.created_at,
        updated_at = type.updated_at,
        width = type.width,
        height = type.height,
        color = type.color,
        blur_hash = type.blur_hash,
        views = type.views,
        downloads = type.downloads,
        likes = type.likes,
        liked_by_user = type.liked_by_user,
        description = type.description,
        alt_description = type.alt_description,
        exif = type.exif,
        location = type.location,
        tags = type.tags,
        current_user_collections = type.current_user_collections,
        sponsorship = type.sponsorship,
        urls = type.urls,
        links = type.links,
        user = type.user,
        statistics = type.statistics
    )
}
