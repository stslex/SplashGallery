package com.stslex.splashgallery.data.data_source.interf

import com.stslex.splashgallery.data.model.domain.collection.CollectionModel
import com.stslex.splashgallery.utils.Result

interface CollectionSource {
    suspend fun getAllCollections(pageNumber: Int): Result<List<CollectionModel>>
}