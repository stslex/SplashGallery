package com.stslex.splashgallery.data.user

import com.stslex.splashgallery.core.Resource
import com.stslex.splashgallery.data.core.DataResponse
import com.stslex.splashgallery.data.model.user.RemoteUserModel
import com.stslex.splashgallery.utils.API_KEY_SUCCESS
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@ExperimentalCoroutinesApi
interface UserRepository {

    suspend fun getUser(username: String): Flow<Resource<RemoteUserModel>>

    class Base @Inject constructor(
        private val service: UserService,
        private val response: DataResponse
    ) : UserRepository {

        override suspend fun getUser(username: String): Flow<Resource<RemoteUserModel>> =
            response.create(service.getUser(username, API_KEY_SUCCESS))
    }
}