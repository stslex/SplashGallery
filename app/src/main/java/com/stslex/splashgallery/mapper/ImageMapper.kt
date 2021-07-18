package com.stslex.splashgallery.mapper

import com.stslex.splashgallery.data.model.ImageModel
import com.stslex.splashgallery.data.model.RemoteImageModel

class ImageMapper : BaseMapper<RemoteImageModel, ImageModel>{
    override fun transformToDomain(type: RemoteImageModel): ImageModel = ImageModel(
        id = type.id,
        created_at = type.created_at,
        updated_at = type.updated_at,
        width = type.width,
        height = type.height,
        color = type.color,
        blur_hash = type.blur_hash,
        likes = type.likes,
        liked_by_user = type.liked_by_user,
        description = type.description,
        user = type.user,
        current_user_collection = type.current_user_collection,
        urls = type.urls,
        links = type.links
    )
}
