package com.stslex.splashgallery.data.repository.impl

import com.stslex.splashgallery.data.model.domain.user.UserModel
import com.stslex.splashgallery.data.repository.interf.UserRepository
import com.stslex.splashgallery.data.service.UserService
import com.stslex.splashgallery.mapper.UserMapper
import com.stslex.splashgallery.utils.API_KEY_SUCCESS
import com.stslex.splashgallery.utils.Result
import com.stslex.splashgallery.utils.base.ResponseSingleEvent
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.channels.trySendBlocking
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val service: UserService
) : UserRepository {
    override suspend fun getUser(username: String): Flow<Result<UserModel>> = callbackFlow {
        val response = service.getUser(username, API_KEY_SUCCESS)
        ResponseSingleEvent(UserMapper(), {
            trySendBlocking(Result.Success(it))
        }, {
            trySendBlocking(Result.Failure(it))
        }).apply {
            response.event()
        }
        awaitClose { }
    }
}