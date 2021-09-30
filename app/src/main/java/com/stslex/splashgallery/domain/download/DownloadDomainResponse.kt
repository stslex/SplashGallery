package com.stslex.splashgallery.domain.download

import com.stslex.splashgallery.data.download.DownloadDataMapper
import com.stslex.splashgallery.data.download.DownloadDataResult
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.channels.trySendBlocking
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.collect
import javax.inject.Inject

@ExperimentalCoroutinesApi
interface DownloadDomainResponse {

    fun create(data: Flow<DownloadDataResult>): Flow<DownloadDomainResult>
    class Base @Inject constructor(
        private val mapper: DownloadDataMapper<DownloadDomainResult>
    ) : DownloadDomainResponse {

        override fun create(data: Flow<DownloadDataResult>): Flow<DownloadDomainResult> =
            callbackFlow {
                data.collection {
                    trySendBlocking(it)
                }
                awaitClose { }
            }

        private suspend inline fun Flow<DownloadDataResult>.collection(
            crossinline function: (DownloadDomainResult) -> Unit
        ) = try {
            this.collect {
                function(it.map(mapper))
            }
        } catch (exception: Exception) {
            function(DownloadDomainResult.Failure(exception))
        }

    }
}