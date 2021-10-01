package com.stslex.splashgallery.data.photo

import com.stslex.splashgallery.data.model.download.RemoteDownloadModel
import com.stslex.splashgallery.data.model.image.RemoteImageModel
import com.stslex.splashgallery.utils.API_KEY_SUCCESS
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@ExperimentalCoroutinesApi
interface PhotoRepository {
    suspend fun getCurrentPhoto(id: String): Flow<DataResult<RemoteImageModel>>
    suspend fun downloadPhoto(id: String): Flow<DataResult<RemoteDownloadModel>>
    class Base @Inject constructor(
        private val service: PhotoService,
        private val response: PhotoDataResponse
    ) : PhotoRepository {

        override suspend fun getCurrentPhoto(id: String): Flow<DataResult<RemoteImageModel>> =
            response.create(service.getCurrentPhoto(id, API_KEY_SUCCESS))

        override suspend fun downloadPhoto(id: String): Flow<DataResult<RemoteDownloadModel>> =
            response.create(service.downloadPhoto(id, API_KEY_SUCCESS))

    }
}