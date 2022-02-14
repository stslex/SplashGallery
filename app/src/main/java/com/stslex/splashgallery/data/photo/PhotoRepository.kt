package com.stslex.splashgallery.data.photo

import com.stslex.splashgallery.core.Resource
import com.stslex.splashgallery.data.model.remote.download.RemoteDownloadModel
import com.stslex.splashgallery.data.utils.DataResponse
import kotlinx.coroutines.flow.Flow
import st.slex.csplashscreen.data.model.remote.image.RemoteImageModel
import javax.inject.Inject

interface PhotoRepository {

    suspend fun getCurrentPhoto(id: String): Flow<Resource<RemoteImageModel>>
    suspend fun downloadPhoto(id: String): Flow<Resource<RemoteDownloadModel>>

    class Base @Inject constructor(
        private val service: PhotoService,
        private val response: DataResponse
    ) : PhotoRepository {

        override suspend fun getCurrentPhoto(id: String): Flow<Resource<RemoteImageModel>> =
            response.create(service.getCurrentPhoto(id))

        override suspend fun downloadPhoto(id: String): Flow<Resource<RemoteDownloadModel>> =
            response.create(service.downloadPhoto(id))
    }
}