package com.stslex.splashgallery.data.photos

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.channels.trySendBlocking
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import retrofit2.Response
import javax.inject.Inject

@ExperimentalCoroutinesApi
interface PhotosDataResponse {

    fun create(response: Response<List<PhotosData.Base>>): Flow<PhotosDataResult>
    class Base @Inject constructor() : PhotosDataResponse {

        override fun create(response: Response<List<PhotosData.Base>>): Flow<PhotosDataResult> =
            callbackFlow {
                response.responseEvent {
                    trySendBlocking(it)
                }
                awaitClose { }
            }

        private inline fun Response<List<PhotosData.Base>>.responseEvent(
            crossinline function: (PhotosDataResult) -> Unit
        ) = try {
            if (isSuccessful && body() != null) {
                body()?.let {
                    function(PhotosDataResult.Success(it))
                }
            } else {
                function(PhotosDataResult.Failure(message().toString()))
            }
        } catch (exception: Exception) {
            function(PhotosDataResult.Failure(exception.toString()))
        }
    }
}