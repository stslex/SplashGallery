package com.stslex.splashgallery.domain.core

import com.stslex.splashgallery.core.Abstract
import com.stslex.splashgallery.data.core.DataResult
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.channels.trySendBlocking
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.collect
import javax.inject.Inject

@ExperimentalCoroutinesApi
interface DomainResponse<D, T> {

    fun create(dataFlow: Flow<DataResult<D>>): Flow<T>
    class Base<D, T> @Inject constructor(
        private val mapper: Abstract.Mapper.DataToDomain<D, T>
    ) : DomainResponse<D, T> {
        override fun create(dataFlow: Flow<DataResult<D>>): Flow<T> = callbackFlow {
            dataFlow.collection {
                trySendBlocking(it)
            }
            awaitClose { }
        }

        private suspend inline fun Flow<DataResult<D>>.collection(
            crossinline function: (T) -> Unit
        ) = try {
            this.collect {
                function(it.map(mapper))
            }
        } catch (exception: Exception) {
            function(DomainResult.Failure(exception.toString()) as T)
        }

    }
}