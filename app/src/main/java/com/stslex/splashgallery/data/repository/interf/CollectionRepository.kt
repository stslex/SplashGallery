package com.stslex.splashgallery.data.repository.interf

import com.stslex.splashgallery.data.model.domain.collection.CollectionModel
import com.stslex.splashgallery.utils.Result
import kotlinx.coroutines.flow.Flow

interface CollectionRepository {
    suspend fun getAllCollections(page: Int): Flow<Result<List<CollectionModel>>>
    suspend fun getUserCollections(username: String, page: Int): Flow<Result<List<CollectionModel>>>
}