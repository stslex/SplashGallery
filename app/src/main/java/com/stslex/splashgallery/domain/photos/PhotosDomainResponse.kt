package com.stslex.splashgallery.domain.photos

import com.stslex.splashgallery.data.photos.PhotosDataMapper
import com.stslex.splashgallery.data.photos.PhotosDataResult
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.channels.trySendBlocking
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.collect
import javax.inject.Inject

@ExperimentalCoroutinesApi
interface PhotosDomainResponse {

    fun create(dataFlow: Flow<PhotosDataResult>): Flow<PhotosDomainResult>
    class Base @Inject constructor(
        private val mapper: PhotosDataMapper<PhotosDomainResult>
    ) : PhotosDomainResponse {
        override fun create(dataFlow: Flow<PhotosDataResult>): Flow<PhotosDomainResult> =
            callbackFlow {
                dataFlow.collection {
                    trySendBlocking(it)
                }
                awaitClose { }
            }

        private suspend inline fun Flow<PhotosDataResult>.collection(
            crossinline function: (PhotosDomainResult) -> Unit
        ) = try {
            this.collect {
                function(it.map(mapper))
            }
        } catch (exception: Exception) {
            function(PhotosDomainResult.Failure(exception.toString()))
        }

    }
}