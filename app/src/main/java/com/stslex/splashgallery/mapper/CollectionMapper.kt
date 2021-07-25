package com.stslex.splashgallery.mapper

import com.stslex.splashgallery.data.model.domain.collection.CollectionModel
import com.stslex.splashgallery.data.model.remote.RemoteCollectionModel

class CollectionMapper : BaseMapper<RemoteCollectionModel, CollectionModel> {
    override fun transformToDomain(type: RemoteCollectionModel): CollectionModel = CollectionModel(
        id = type.id,
        title = type.title,
        description = type.description,
        published_at = type.published_at,
        updated_at = type.updated_at,
        curated = type.curated,
        featured = type.featured,
        total_photos = type.total_photos,
        private = type.private,
        share_key = type.share_key,
        tags = type.tags,
        cover_photo = type.cover_photo,
        preview_photos = type.preview_photos,
        user = type.user,
        links = type.links
    )
}