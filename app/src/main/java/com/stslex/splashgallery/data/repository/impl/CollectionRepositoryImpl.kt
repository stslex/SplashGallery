package com.stslex.splashgallery.data.repository.impl

import com.stslex.splashgallery.data.model.domain.collection.CollectionModel
import com.stslex.splashgallery.data.repository.interf.CollectionRepository
import com.stslex.splashgallery.data.service.CollectionService
import com.stslex.splashgallery.mapper.CollectionMapper
import com.stslex.splashgallery.utils.API_KEY_SUCCESS
import com.stslex.splashgallery.utils.Result
import com.stslex.splashgallery.utils.base.ResponseEvent
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.channels.trySendBlocking
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import javax.inject.Inject

@ExperimentalCoroutinesApi
class CollectionRepositoryImpl @Inject constructor(private val service: CollectionService) :
    CollectionRepository {
    override suspend fun getAllCollections(page: Int): Flow<Result<List<CollectionModel>>> =
        callbackFlow {
            val response = service.getAllCollections(page, API_KEY_SUCCESS)
            ResponseEvent(
                CollectionMapper(),
                { trySendBlocking(Result.Success(it)) },
                { trySendBlocking(Result.Failure(it)) })
                .apply { response.event() }
            awaitClose { }
        }

    override suspend fun getUserCollections(
        username: String,
        page: Int
    ): Flow<Result<List<CollectionModel>>> = callbackFlow {
        val response = service.getUserCollections(username, page, API_KEY_SUCCESS)
        ResponseEvent(
            CollectionMapper(),
            { trySendBlocking(Result.Success(it)) },
            { trySendBlocking(Result.Failure(it)) })
            .apply { response.event() }
        awaitClose { }
    }

}