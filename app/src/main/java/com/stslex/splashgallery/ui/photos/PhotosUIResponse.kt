package com.stslex.splashgallery.ui.photos

import com.stslex.splashgallery.domain.photos.PhotosDomainMapper
import com.stslex.splashgallery.domain.photos.PhotosDomainResult
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.channels.trySendBlocking
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.collect
import javax.inject.Inject

@ExperimentalCoroutinesApi
interface PhotosUIResponse {

    suspend fun mapIt(dataFlow: Flow<PhotosDomainResult>): Flow<PhotosUIResult>

    class Base @Inject constructor(
        private val mapper: PhotosDomainMapper<PhotosUIResult>
    ) : PhotosUIResponse {

        override suspend fun mapIt(dataFlow: Flow<PhotosDomainResult>): Flow<PhotosUIResult> =
            callbackFlow {
                dataFlow.response {
                    trySendBlocking(it)
                }
                awaitClose { }
            }

        private suspend inline fun Flow<PhotosDomainResult>.response(
            crossinline function: (PhotosUIResult) -> Unit
        ) = try {
            this@response.collect {
                function(it.map(mapper))
            }
        } catch (exception: Exception) {
            PhotosUIResult.Failure(exception.toString())
        }
    }
}