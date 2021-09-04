package com.stslex.splashgallery.data.repository.interf

import com.stslex.splashgallery.data.model.domain.user.UserModel
import com.stslex.splashgallery.utils.Result
import kotlinx.coroutines.flow.Flow

interface UserRepository {
    suspend fun getUser(username: String): Flow<Result<UserModel>>
}