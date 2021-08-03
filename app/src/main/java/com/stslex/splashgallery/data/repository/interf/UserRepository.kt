package com.stslex.splashgallery.data.repository.interf

import com.stslex.splashgallery.data.model.domain.user.UserModel
import com.stslex.splashgallery.utils.Result

interface UserRepository {
    suspend fun getUserInfo(username: String): Result<UserModel>
    suspend fun <T> getUserContent(username: String, content: String, page: Int): Result<T?>
}