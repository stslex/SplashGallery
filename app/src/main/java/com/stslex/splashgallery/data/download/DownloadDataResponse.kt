package com.stslex.splashgallery.data.download

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.channels.trySendBlocking
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import retrofit2.Response
import javax.inject.Inject

@ExperimentalCoroutinesApi
interface DownloadDataResponse {

    fun create(response: Response<DownloadData.Base>): Flow<DownloadDataResult>

    class Base @Inject constructor() : DownloadDataResponse {

        override fun create(response: Response<DownloadData.Base>): Flow<DownloadDataResult> =
            callbackFlow {
                response.responseEvent {
                    trySendBlocking(it)
                }
                awaitClose { }
            }

        private inline fun Response<DownloadData.Base>.responseEvent(
            crossinline function: (DownloadDataResult) -> Unit
        ) = try {
            if (isSuccessful && body() != null) {
                body()?.let {
                    function(DownloadDataResult.Success(it))
                }
            } else {
                function(DownloadDataResult.Failure(message().toString()))
            }
        } catch (exception: Exception) {
            function(DownloadDataResult.Failure(exception.toString()))
        }

    }
}