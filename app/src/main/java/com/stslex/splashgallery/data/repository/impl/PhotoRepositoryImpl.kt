package com.stslex.splashgallery.data.repository.impl

import com.stslex.splashgallery.data.model.domain.image.ImageModel
import com.stslex.splashgallery.data.repository.interf.PhotoRepository
import com.stslex.splashgallery.data.service.PhotoService
import com.stslex.splashgallery.mapper.ImageMapper
import com.stslex.splashgallery.utils.API_KEY_SUCCESS
import com.stslex.splashgallery.utils.Result
import com.stslex.splashgallery.utils.base.ResponseSingleEvent
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.channels.trySendBlocking
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import javax.inject.Inject

@ExperimentalCoroutinesApi
class PhotoRepositoryImpl @Inject constructor(
    private val service: PhotoService
) : PhotoRepository {
    override suspend fun getCurrentPhoto(id: String): Flow<Result<ImageModel>> = callbackFlow {
        val response = service.getCurrentPhoto(id, API_KEY_SUCCESS)
        ResponseSingleEvent(ImageMapper(), {
            trySendBlocking(Result.Success(it))
        }, {
            trySendBlocking(Result.Failure(it))
        }).apply {
            response.event()
        }
        awaitClose { }
    }
}