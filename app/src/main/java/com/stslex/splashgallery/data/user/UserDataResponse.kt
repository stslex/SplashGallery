package com.stslex.splashgallery.data.user

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.channels.trySendBlocking
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import retrofit2.Response
import javax.inject.Inject

@ExperimentalCoroutinesApi
interface UserDataResponse {

    fun create(response: Response<UserData.Base>): Flow<UserDataResult>
    class Base @Inject constructor() : UserDataResponse {

        override fun create(response: Response<UserData.Base>): Flow<UserDataResult> =
            callbackFlow {
                response.responseEvent {
                    trySendBlocking(it)
                }
                awaitClose { }
            }

        private inline fun Response<UserData.Base>.responseEvent(
            crossinline function: (UserDataResult) -> Unit
        ) = try {
            if (isSuccessful && body() != null) {
                body()?.let {
                    function(UserDataResult.Success(it))
                }
            } else {
                function(UserDataResult.Failure(message().toString()))
            }
        } catch (exception: Exception) {
            function(UserDataResult.Failure(exception.toString()))
        }
    }
}