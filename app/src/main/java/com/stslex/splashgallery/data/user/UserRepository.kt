package com.stslex.splashgallery.data.user

import com.stslex.splashgallery.core.Resource
import com.stslex.splashgallery.data.utils.DataResponse
import kotlinx.coroutines.flow.Flow
import st.slex.csplashscreen.data.model.remote.user.RemoteUserModel
import javax.inject.Inject

interface UserRepository {

    suspend fun getUser(username: String): Flow<Resource<RemoteUserModel>>

    class Base @Inject constructor(
        private val service: UserService,
        private val response: DataResponse
    ) : UserRepository {

        override suspend fun getUser(username: String): Flow<Resource<RemoteUserModel>> =
            response.create(service.getUser(username))
    }
}