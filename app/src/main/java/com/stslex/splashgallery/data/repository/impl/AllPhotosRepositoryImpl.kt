package com.stslex.splashgallery.data.repository.impl

import com.stslex.splashgallery.data.model.domain.image.ImageModel
import com.stslex.splashgallery.data.repository.interf.AllPhotosRepository
import com.stslex.splashgallery.data.service.AllPhotosService
import com.stslex.splashgallery.mapper.ImageMapper
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
class AllPhotosRepositoryImpl @Inject constructor(private val service: AllPhotosService) :
    AllPhotosRepository {

    override suspend fun getAllPhotos(page: Int): Flow<Result<List<ImageModel>>> =
        callbackFlow {
            val response = service.getAllPhotos(page, API_KEY_SUCCESS)
            ResponseEvent(
                ImageMapper(),
                { trySendBlocking(Result.Success(it)) },
                { trySendBlocking(Result.Failure(it)) })
                .apply { response.event() }
            awaitClose { }
        }

    override suspend fun getUserPhotos(
        username: String,
        page: Int
    ): Flow<Result<List<ImageModel>>> = callbackFlow {
        val response = service.getUserPhotos(username, page, API_KEY_SUCCESS)
        ResponseEvent(
            ImageMapper(),
            { trySendBlocking(Result.Success(it)) },
            { trySendBlocking(Result.Failure(it)) })
            .apply { response.event() }
        awaitClose { }
    }

    override suspend fun getUserLikes(
        username: String,
        page: Int
    ): Flow<Result<List<ImageModel>>> =
        callbackFlow {
            val response = service.getUserLikes(username, page, API_KEY_SUCCESS)
            ResponseEvent(
                ImageMapper(),
                { trySendBlocking(Result.Success(it)) },
                { trySendBlocking(Result.Failure(it)) })
                .apply { response.event() }
            awaitClose { }
        }

    override suspend fun getCollectionPhotos(
        id: String,
        page: Int
    ): Flow<Result<List<ImageModel>>> =
        callbackFlow {
            val response = service.getCollectionPhotos(id, page, API_KEY_SUCCESS)
            ResponseEvent(
                ImageMapper(),
                { trySendBlocking(Result.Success(it)) },
                { trySendBlocking(Result.Failure(it)) })
                .apply { response.event() }
            awaitClose { }
        }
}