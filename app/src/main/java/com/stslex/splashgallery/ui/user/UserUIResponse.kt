package com.stslex.splashgallery.ui.user

import com.stslex.splashgallery.domain.user.UserDomainMapper
import com.stslex.splashgallery.domain.user.UserDomainResult
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.channels.trySendBlocking
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.collect
import javax.inject.Inject

@ExperimentalCoroutinesApi
interface UserUIResponse {

    suspend fun create(data: Flow<UserDomainResult>): Flow<UserUIResult>

    class Base @Inject constructor(
        private val mapper: UserDomainMapper<UserUIResult>
    ) : UserUIResponse {

        override suspend fun create(data: Flow<UserDomainResult>): Flow<UserUIResult> =
            callbackFlow {
                data.response {
                    trySendBlocking(it)
                }
                awaitClose { }
            }

        private suspend inline fun Flow<UserDomainResult>.response(
            crossinline function: (UserUIResult) -> Unit
        ) = try {
            this@response.collect {
                function(it.map(mapper))
            }
        } catch (exception: Exception) {
            UserUIResult.Failure(exception.toString())
        }
    }
}