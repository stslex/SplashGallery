package com.stslex.splashgallery.data.repository.impl

import com.stslex.splashgallery.data.base.interf.CreateResponse
import com.stslex.splashgallery.data.model.domain.user.UserModel
import com.stslex.splashgallery.data.model.remote.RemoteUserModel
import com.stslex.splashgallery.data.repository.interf.UserRepository
import com.stslex.splashgallery.data.service.UserService
import com.stslex.splashgallery.utils.API_KEY_SUCCESS
import com.stslex.splashgallery.utils.Result
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@ExperimentalCoroutinesApi
class UserRepositoryImpl @Inject constructor(
    private val service: UserService,
    private val response: CreateResponse<RemoteUserModel, UserModel>
) : UserRepository {

    override suspend fun getUser(username: String): Flow<Result<UserModel>> =
        response.createSingle(service.getUser(username, API_KEY_SUCCESS))

}