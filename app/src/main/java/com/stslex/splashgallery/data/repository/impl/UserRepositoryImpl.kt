package com.stslex.splashgallery.data.repository.impl

import com.stslex.splashgallery.data.data_source.interf.UserSource
import com.stslex.splashgallery.data.model.domain.user.UserModel
import com.stslex.splashgallery.data.repository.interf.UserRepository
import com.stslex.splashgallery.utils.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(private val userSource: UserSource) : UserRepository {
    override suspend fun getUserInfo(username: String): Result<UserModel> =
        withContext(Dispatchers.IO) {
            return@withContext try {
                when (val response = userSource.getUserInfo(username)) {
                    is Result.Success -> {
                        Result.Success(response.data)
                    }
                    is Result.Failure -> {
                        Result.Failure(response.exception)
                    }
                    else -> {
                        Result.Loading
                    }
                }
            } catch (exception: Exception) {
                Result.Failure(exception.toString())
            }
        }
}