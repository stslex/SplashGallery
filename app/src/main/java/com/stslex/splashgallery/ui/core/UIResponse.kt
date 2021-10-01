package com.stslex.splashgallery.ui.core

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
interface UIResponse {

    suspend fun <D, U> getAndMap(
        flow: Flow<DataResult<D>>,
        mapper: Abstract.Mapper.DataToUI<D, UIResult<U>>
    ): Flow<UIResult<U>>

    class Base @Inject constructor() : UIResponse {

        override suspend fun <D, U> getAndMap(
            flow: Flow<DataResult<D>>,
            mapper: Abstract.Mapper.DataToUI<D, UIResult<U>>
        ): Flow<UIResult<U>> =
            callbackFlow {
                flow.startCollecting(mapper) {
                    trySendBlocking(it)
                }
                awaitClose { }
            }

        private suspend inline fun <D, T> Flow<DataResult<D>>.startCollecting(
            mapper: Abstract.Mapper.DataToUI<D, UIResult<T>>,
            crossinline function: (UIResult<T>) -> Unit,
        ) = try {
            collect {
                function(it.map(mapper))
            }
        } catch (exception: Exception) {
            function(UIResult.Failure(exception))
        }
    }
}