package com.stslex.splashgallery.data.data_source.interf

import com.stslex.splashgallery.data.model.domain.user.UserModel
import com.stslex.splashgallery.utils.Result

interface UserSource {
    suspend fun getUserInfo(username: String): Result<UserModel>
    suspend fun <T> getUserContent(username: String, content: String, page: Int): Result<T?>
}