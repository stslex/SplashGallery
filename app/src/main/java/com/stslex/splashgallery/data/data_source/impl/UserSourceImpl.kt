package com.stslex.splashgallery.data.data_source.impl

import com.stslex.splashgallery.data.data_source.interf.UserSource
import com.stslex.splashgallery.data.model.domain.user.UserModel
import com.stslex.splashgallery.data.model.remote.RemoteUserModel
import com.stslex.splashgallery.data.service.UserService
import com.stslex.splashgallery.mapper.UserMapper
import com.stslex.splashgallery.utils.API_KEY_SUCCESS
import com.stslex.splashgallery.utils.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class UserSourceImpl @Inject constructor(private val userService: UserService) : UserSource {
    override suspend fun getUserInfo(username: String): Result<UserModel> =
        withContext(Dispatchers.IO) {
            return@withContext try {
                val result = userService.getUserInfo(username, API_KEY_SUCCESS)
                if (result.isSuccessful && result.body() != null) {
                    val mapper = UserMapper()
                    val remoteUser = result.body() as RemoteUserModel
                    val user = mapper.transformToDomain(remoteUser)
                    Result.Success(user)
                } else {
                    Result.Failure("Null result")
                }
            } catch (exception: Exception) {
                Result.Failure(exception.toString())
            }
        }

    override suspend fun <T> getUserContent(
        username: String,
        content: String,
        page: Int
    ): Result<T?> = withContext(Dispatchers.IO) {
        return@withContext try {
            val result = userService.getUserContent<T>(
                username = username,
                content = content,
                page = page,
                api_key = API_KEY_SUCCESS
            )
            if (result.isSuccessful && result.body() != null) {
                Result.Success(result.body())
            } else {
                Result.Failure("Null request")
            }
        } catch (exception: Exception) {
            Result.Failure(exception = exception.toString())
        }
    }


}