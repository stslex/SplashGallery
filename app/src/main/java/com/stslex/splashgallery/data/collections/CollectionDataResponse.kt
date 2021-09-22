package com.stslex.splashgallery.data.collections

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.channels.trySendBlocking
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import retrofit2.Response
import javax.inject.Inject

@ExperimentalCoroutinesApi
interface CollectionDataResponse {

    fun create(response: Response<List<CollectionData.Base>>): Flow<CollectionDataResult>
    class Base @Inject constructor() : CollectionDataResponse {

        override fun create(response: Response<List<CollectionData.Base>>): Flow<CollectionDataResult> =
            callbackFlow {
                response.responseEvent {
                    trySendBlocking(it)
                }
                awaitClose { }
            }

        private inline fun Response<List<CollectionData.Base>>.responseEvent(
            crossinline function: (CollectionDataResult) -> Unit
        ) = try {
            if (isSuccessful && body() != null) {
                body()?.let {
                    function(CollectionDataResult.Success(it))
                }
            } else {
                function(CollectionDataResult.Failure(message().toString()))
            }
        } catch (exception: Exception) {
            function(CollectionDataResult.Failure(exception.toString()))
        }

    }
}