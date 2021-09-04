package com.stslex.splashgallery.data.repository.impl

import com.stslex.splashgallery.data.model.domain.DownloadModel
import com.stslex.splashgallery.data.repository.interf.DownloadRepository
import com.stslex.splashgallery.data.service.DownloadService
import com.stslex.splashgallery.mapper.DownloadMapper
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
class DownloadRepositoryImpl @Inject constructor(
    private val service: DownloadService
) : DownloadRepository {
    override suspend fun downloadPhoto(id: String): Flow<Result<DownloadModel>> = callbackFlow {
        val response = service.downloadPhoto(id = id, api_key = API_KEY_SUCCESS)
        ResponseSingleEvent(DownloadMapper(), {
            trySendBlocking(Result.Success(it))
        }, {
            trySendBlocking(Result.Failure(it))
        }).apply {
            response.event()
        }
        awaitClose { }
    }
}