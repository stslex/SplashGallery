package com.stslex.splashgallery.data.photo

import com.stslex.splashgallery.utils.API_KEY_SUCCESS
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@ExperimentalCoroutinesApi
interface PhotoRepository {
    suspend fun getCurrentPhoto(id: String): Flow<PhotoDataResult>

    class Base @Inject constructor(
        private val service: PhotoService,
        private val response: PhotoDataResponse
    ) : PhotoRepository {

        override suspend fun getCurrentPhoto(id: String): Flow<PhotoDataResult> =
            response.create(service.getCurrentPhoto(id, API_KEY_SUCCESS))

    }
}