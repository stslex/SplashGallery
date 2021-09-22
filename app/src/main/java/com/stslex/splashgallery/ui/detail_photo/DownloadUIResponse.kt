package com.stslex.splashgallery.ui.detail_photo

import com.stslex.splashgallery.domain.download.DownloadDomainMapper
import com.stslex.splashgallery.domain.download.DownloadDomainResult
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.channels.trySendBlocking
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.collect
import javax.inject.Inject

@ExperimentalCoroutinesApi
interface DownloadUIResponse {

    suspend fun create(data: Flow<DownloadDomainResult>): Flow<DownloadUIResult>

    class Base @Inject constructor(
        private val mapper: DownloadDomainMapper<DownloadUIResult>
    ) : DownloadUIResponse {

        override suspend fun create(data: Flow<DownloadDomainResult>): Flow<DownloadUIResult> =
            callbackFlow {
                data.response {
                    trySendBlocking(it)
                }
                awaitClose { }
            }

        private suspend inline fun Flow<DownloadDomainResult>.response(
            crossinline function: (DownloadUIResult) -> Unit
        ) = try {
            this@response.collect {
                function(it.map(mapper))
            }
        } catch (exception: Exception) {
            DownloadUIResult.Failure(exception.toString())
        }
    }
}