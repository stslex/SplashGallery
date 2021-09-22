package com.stslex.splashgallery.domain.collections

import com.stslex.splashgallery.data.collections.CollectionRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@ExperimentalCoroutinesApi
interface CollectionInteractor {

    suspend fun getAllCollections(page: Int): Flow<CollectionDomainResult>
    suspend fun getUserCollections(username: String, page: Int): Flow<CollectionDomainResult>

    class Base @Inject constructor(
        private val repository: CollectionRepository,
        private val response: CollectionDomainResponse
    ) : CollectionInteractor {
        override suspend fun getAllCollections(page: Int): Flow<CollectionDomainResult> =
            response.create(repository.getAllCollections(page))

        override suspend fun getUserCollections(
            username: String,
            page: Int
        ): Flow<CollectionDomainResult> =
            response.create(repository.getUserCollections(username, page))

    }
}