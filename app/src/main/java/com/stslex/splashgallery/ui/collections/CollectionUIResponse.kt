package com.stslex.splashgallery.ui.collections

import com.stslex.splashgallery.domain.collections.CollectionDomainMapper
import com.stslex.splashgallery.domain.collections.CollectionDomainResult
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.channels.trySendBlocking
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.collect
import javax.inject.Inject

@ExperimentalCoroutinesApi
interface CollectionUIResponse {

    suspend fun create(dataFlow: Flow<CollectionDomainResult>): Flow<CollectionUIResult>

    class Base @Inject constructor(
        private val mapper: CollectionDomainMapper<CollectionUIResult>
    ) : CollectionUIResponse {

        override suspend fun create(dataFlow: Flow<CollectionDomainResult>): Flow<CollectionUIResult> =
            callbackFlow {
                dataFlow.response {
                    trySendBlocking(it)
                }
                awaitClose { }
            }

        private suspend inline fun Flow<CollectionDomainResult>.response(
            crossinline function: (CollectionUIResult) -> Unit
        ) = try {
            this@response.collect {
                function(it.map(mapper))
            }
        } catch (exception: Exception) {
            CollectionUIResult.Failure(exception.toString())
        }
    }
}