package com.stslex.splashgallery.data.repository.interf

import com.stslex.splashgallery.data.model.domain.collection.CollectionModel
import com.stslex.splashgallery.utils.Result

interface CollectionRepository {
    suspend fun getAllCollections(pageNumber: Int): Result<List<CollectionModel>>
}