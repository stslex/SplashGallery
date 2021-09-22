package com.stslex.splashgallery.domain.user

import com.stslex.splashgallery.data.user.UserDataMapper
import com.stslex.splashgallery.data.user.UserDataResult
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.channels.trySendBlocking
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.collect
import javax.inject.Inject

@ExperimentalCoroutinesApi
interface UserDomainResponse {

    fun create(dataFlow: Flow<UserDataResult>): Flow<UserDomainResult>
    class Base @Inject constructor(
        private val mapper: UserDataMapper<UserDomainResult>
    ) : UserDomainResponse {
        override fun create(dataFlow: Flow<UserDataResult>): Flow<UserDomainResult> =
            callbackFlow {
                dataFlow.collection {
                    trySendBlocking(it)
                }
                awaitClose { }
            }

        private suspend inline fun Flow<UserDataResult>.collection(
            crossinline function: (UserDomainResult) -> Unit
        ) = try {
            this.collect {
                function(it.map(mapper))
            }
        } catch (exception: Exception) {
            function(UserDomainResult.Failure(exception.toString()))
        }

    }
}