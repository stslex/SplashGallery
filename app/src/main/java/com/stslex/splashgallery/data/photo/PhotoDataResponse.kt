package com.stslex.splashgallery.data.photo

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.channels.trySendBlocking
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import retrofit2.Response
import javax.inject.Inject

@ExperimentalCoroutinesApi
interface PhotoDataResponse {

    fun create(response: Response<PhotoData.Base>): Flow<PhotoDataResult>
    class Base @Inject constructor() : PhotoDataResponse {

        override fun create(response: Response<PhotoData.Base>): Flow<PhotoDataResult> =
            callbackFlow {
                response.responseEvent {
                    trySendBlocking(it)
                }
                awaitClose { }
            }

        private inline fun Response<PhotoData.Base>.responseEvent(
            crossinline function: (PhotoDataResult) -> Unit
        ) = try {
            if (isSuccessful && body() != null) {
                body()?.let {
                    function(PhotoDataResult.Success(it))
                }
            } else {
                function(PhotoDataResult.Failure(Exception(errorBody().toString())))
            }
        } catch (exception: Exception) {
            function(PhotoDataResult.Failure(exception))
        }
    }
}