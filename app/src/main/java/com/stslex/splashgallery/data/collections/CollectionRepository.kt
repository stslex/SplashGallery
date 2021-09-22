package com.stslex.splashgallery.data.collections

import com.stslex.splashgallery.utils.API_KEY_SUCCESS
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface CollectionRepository {

    suspend fun getAllCollections(page: Int): Flow<CollectionDataResult>
    suspend fun getUserCollections(username: String, page: Int): Flow<CollectionDataResult>

    class Base @Inject constructor(
        private val service: CollectionService,
        private val response: CollectionDataResponse
    ) : CollectionRepository {
        override suspend fun getAllCollections(page: Int): Flow<CollectionDataResult> =
            response.create(service.getAllCollections(page, API_KEY_SUCCESS))

        override suspend fun getUserCollections(
            username: String,
            page: Int
        ): Flow<CollectionDataResult> =
            response.create(service.getUserCollections(username, page, API_KEY_SUCCESS))

    }
}