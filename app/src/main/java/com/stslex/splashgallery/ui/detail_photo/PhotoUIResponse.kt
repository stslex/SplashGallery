package com.stslex.splashgallery.ui.detail_photo

import com.stslex.splashgallery.domain.photo.PhotoDomainMapper
import com.stslex.splashgallery.domain.photo.PhotoDomainResult
import com.stslex.splashgallery.ui.photos.PhotosUIResult
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.channels.trySendBlocking
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.collect
import javax.inject.Inject


@ExperimentalCoroutinesApi
interface PhotoUIResponse {

    suspend fun create(data: Flow<PhotoDomainResult>): Flow<PhotoUIResult>

    class Base @Inject constructor(
        private val mapper: PhotoDomainMapper<PhotoUIResult>
    ) : PhotoUIResponse {

        override suspend fun create(data: Flow<PhotoDomainResult>): Flow<PhotoUIResult> =
            callbackFlow {
                data.response {
                    trySendBlocking(it)
                }
                awaitClose { }
            }

        private suspend inline fun Flow<PhotoDomainResult>.response(
            crossinline function: (PhotoUIResult) -> Unit
        ) = try {
            this@response.collect {
                function(it.map(mapper))
            }
        } catch (exception: Exception) {
            PhotosUIResult.Failure(exception.toString())
        }
    }
}