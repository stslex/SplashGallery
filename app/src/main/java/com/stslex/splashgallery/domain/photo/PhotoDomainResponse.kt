package com.stslex.splashgallery.domain.photo

import com.stslex.splashgallery.data.photo.PhotoDataMapper
import com.stslex.splashgallery.data.photo.PhotoDataResult
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.channels.trySendBlocking
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.collect
import javax.inject.Inject

@ExperimentalCoroutinesApi
interface PhotoDomainResponse {

    fun create(dataFlow: Flow<PhotoDataResult>): Flow<PhotoDomainResult>
    class Base @Inject constructor(
        private val mapper: PhotoDataMapper<PhotoDomainResult>
    ) : PhotoDomainResponse {
        override fun create(dataFlow: Flow<PhotoDataResult>): Flow<PhotoDomainResult> =
            callbackFlow {
                dataFlow.collection {
                    trySendBlocking(it)
                }
                awaitClose { }
            }

        private suspend inline fun Flow<PhotoDataResult>.collection(
            crossinline function: (PhotoDomainResult) -> Unit
        ) = try {
            this.collect {
                function(it.map(mapper))
            }
        } catch (exception: Exception) {
            function(PhotoDomainResult.Failure(exception.toString()))
        }

    }
}