package com.stslex.splashgallery.data.photo

import com.stslex.core.Resource
import com.stslex.core_model.response.download.RemoteDownloadModel
import com.stslex.splashgallery.data.utils.DataResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import st.slex.csplashscreen.data.model.remote.image.RemoteImageModel
import javax.inject.Inject

interface PhotoRepository {

    val currentPhoto: (id: String) -> Flow<Resource<RemoteImageModel>>
    suspend fun downloadPhoto(id: String): Flow<Resource<RemoteDownloadModel>>

    class Base @Inject constructor(
        private val service: PhotoService,
        private val response: DataResponse
    ) : PhotoRepository {

        override val currentPhoto: (id: String) -> Flow<Resource<RemoteImageModel>> = { id ->
            flow {
                val imageResponse = service.getCurrentPhoto(id)
                val result = response.createRow(imageResponse)
                emit(result)
            }
        }

        override suspend fun downloadPhoto(id: String): Flow<Resource<RemoteDownloadModel>> =
            response.create(service.downloadPhoto(id))
    }
}