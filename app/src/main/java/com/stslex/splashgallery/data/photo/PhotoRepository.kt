package com.stslex.splashgallery.data.photo

import com.stslex.splashgallery.core.Resource
import com.stslex.splashgallery.data.core.DataResponse
import com.stslex.splashgallery.data.model.download.RemoteDownloadModel
import com.stslex.splashgallery.data.model.image.RemoteImageModel
import com.stslex.splashgallery.utils.API_KEY_SUCCESS
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@ExperimentalCoroutinesApi
interface PhotoRepository {

    suspend fun getCurrentPhoto(id: String): Flow<Resource<RemoteImageModel>>
    suspend fun downloadPhoto(id: String): Flow<Resource<RemoteDownloadModel>>

    class Base @Inject constructor(
        private val service: PhotoService,
        private val response: DataResponse
    ) : PhotoRepository {

        override suspend fun getCurrentPhoto(id: String): Flow<Resource<RemoteImageModel>> =
            response.create(service.getCurrentPhoto(id, API_KEY_SUCCESS))

        override suspend fun downloadPhoto(id: String): Flow<Resource<RemoteDownloadModel>> =
            response.create(service.downloadPhoto(id, API_KEY_SUCCESS))
    }
}