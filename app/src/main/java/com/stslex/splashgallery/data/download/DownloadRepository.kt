package com.stslex.splashgallery.data.download

import com.stslex.splashgallery.utils.API_KEY_SUCCESS
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface DownloadRepository {

    suspend fun downloadPhoto(id: String): Flow<DownloadDataResult>

    @ExperimentalCoroutinesApi
    class Base @Inject constructor(
        private val service: DownloadService,
        private val response: DownloadDataResponse
    ) : DownloadRepository {

        override suspend fun downloadPhoto(id: String): Flow<DownloadDataResult> =
            response.create(service.downloadPhoto(id = id, api_key = API_KEY_SUCCESS))

    }
}