package com.stslex.splashgallery.domain.collections

import com.stslex.splashgallery.data.collections.CollectionDataMapper
import com.stslex.splashgallery.data.collections.CollectionDataResult
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.channels.trySendBlocking
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.collect
import javax.inject.Inject

@ExperimentalCoroutinesApi
interface CollectionDomainResponse {

    fun create(dataFlow: Flow<CollectionDataResult>): Flow<CollectionDomainResult>
    class Base @Inject constructor(
        private val mapper: CollectionDataMapper<CollectionDomainResult>
    ) : CollectionDomainResponse {

        override fun create(dataFlow: Flow<CollectionDataResult>): Flow<CollectionDomainResult> =
            callbackFlow {
                dataFlow.collection {
                    trySendBlocking(it)
                }
                awaitClose { }
            }

        private suspend inline fun Flow<CollectionDataResult>.collection(
            crossinline function: (CollectionDomainResult) -> Unit
        ) = try {
            this.collect {
                function(it.map(mapper))
            }
        } catch (exception: Exception) {
            function(CollectionDomainResult.Failure(exception.toString()))
        }

    }
}