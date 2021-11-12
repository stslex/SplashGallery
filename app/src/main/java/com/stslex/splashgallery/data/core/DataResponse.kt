package com.stslex.splashgallery.data.core

import com.stslex.splashgallery.core.Resource
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.channels.trySendBlocking
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import retrofit2.Response
import javax.inject.Inject

@ExperimentalCoroutinesApi
interface DataResponse {

    fun <T> create(response: Response<T>): Flow<Resource<T>>

    class Base @Inject constructor() : DataResponse {

        override fun <T> create(response: Response<T>): Flow<Resource<T>> =
            callbackFlow {
                response.responseEvent {
                    trySendBlocking(it)
                }
                awaitClose { }
            }

        private inline fun <T> Response<T>.responseEvent(
            crossinline function: (Resource<T>) -> Unit
        ) = try {
            if (isSuccessful && body() != null) {
                body()?.let {
                    function(Resource.Success(it))
                }
            } else {
                function(Resource.Failure(Exception(errorBody().toString())))
            }
        } catch (exception: Exception) {
            function(Resource.Failure(exception))
        }
    }
}