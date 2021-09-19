package com.stslex.splashgallery.data.core

import com.stslex.splashgallery.mapper.BaseMapper
import com.stslex.splashgallery.utils.Result
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.channels.trySendBlocking
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import retrofit2.Response

interface CreateResponse<E, D> {
    suspend fun create(response: Response<List<E>>): Flow<Result<List<D>>>
    suspend fun createSingle(response: Response<E>): Flow<Result<D>>

    @ExperimentalCoroutinesApi
    class Base<E, D>(private val mapper: BaseMapper<E, D>) : CreateResponse<E, D> {

        override suspend fun createSingle(
            response: Response<E>
        ): Flow<Result<D>> =
            callbackFlow {
                ResponseEvent(mapper).apply {
                    response.eventSingle(
                        { trySendBlocking(Result.Success(it)) },
                        { trySendBlocking(Result.Failure(it)) }
                    )
                }
                awaitClose { }
            }

        override suspend fun create(response: Response<List<E>>): Flow<Result<List<D>>> =
            callbackFlow {
                ResponseEvent(mapper).apply {
                    response.event(
                        { trySendBlocking(Result.Success(it)) },
                        { trySendBlocking(Result.Failure(it)) }
                    )
                }
                awaitClose { }
            }
    }
}