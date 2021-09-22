package com.stslex.splashgallery.data.user

import com.stslex.splashgallery.utils.API_KEY_SUCCESS
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@ExperimentalCoroutinesApi
interface UserRepository {

    suspend fun getUser(username: String): Flow<UserDataResult>

    class Base
    @Inject constructor(
        private val service: UserService,
        private val response: UserDataResponse
    ) : UserRepository {

        override suspend fun getUser(username: String): Flow<UserDataResult> =
            response.create(service.getUser(username, API_KEY_SUCCESS))
    }
}