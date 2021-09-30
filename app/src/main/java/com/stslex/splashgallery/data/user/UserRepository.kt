package com.stslex.splashgallery.data.user

import com.stslex.splashgallery.data.model.user.RemoteUserModel
import com.stslex.splashgallery.utils.API_KEY_SUCCESS
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.channels.trySendBlocking
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import retrofit2.Response
import javax.inject.Inject

@ExperimentalCoroutinesApi
interface UserRepository {

    suspend fun getUser(username: String): Flow<UserDataResult>

    class Base
    @Inject constructor(
        private val service: UserService,
    ) : UserRepository {

        override suspend fun getUser(username: String): Flow<UserDataResult> =
            create(service.getUser(username, API_KEY_SUCCESS))

        private fun create(response: Response<RemoteUserModel>): Flow<UserDataResult> =
            callbackFlow {
                response.responseEvent {
                    trySendBlocking(it)
                }
                awaitClose { }
            }

        private inline fun Response<RemoteUserModel>.responseEvent(
            crossinline function: (UserDataResult) -> Unit
        ) = try {
            if (isSuccessful && body() != null) {
                body()?.let {
                    function(UserDataResult.Success(it))
                }
            } else {
                function(UserDataResult.Failure(Exception(errorBody().toString())))
            }
        } catch (exception: Exception) {
            function(UserDataResult.Failure(exception))
        }
    }


}