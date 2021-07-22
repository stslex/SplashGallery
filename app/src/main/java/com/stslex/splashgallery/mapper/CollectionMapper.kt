package com.stslex.splashgallery.mapper

import com.stslex.splashgallery.data.model.domain.collection.CollectionModel
import com.stslex.splashgallery.data.model.remote.RemoteCollectionModel

class CollectionMapper : BaseMapper<RemoteCollectionModel, CollectionModel> {
    override fun transformToDomain(type: RemoteCollectionModel): CollectionModel = CollectionModel(
        id = type.id,
        title = type.title,
        description = type.description,
        published_at = type.published_at,
        last_collected_at = type.last_collected_at,
        updated_at = type.updated_at,
        total_photos = type.total_photos,
        private = type.private,
        share_key = type.share_key,
        cover_photo = type.cover_photo,
        user = type.user,
        links = type.links
    )
}