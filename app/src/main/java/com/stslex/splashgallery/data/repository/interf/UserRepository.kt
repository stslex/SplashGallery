package com.stslex.splashgallery.data.repository.interf

import com.stslex.splashgallery.data.model.domain.collection.CollectionModel
import com.stslex.splashgallery.data.model.domain.user.UserModel
import com.stslex.splashgallery.utils.Result

interface UserRepository {
    suspend fun getUserInfo(username: String): Result<UserModel>
    suspend fun getUserContentCollections(
        username: String,
        page: Int
    ): Result<List<CollectionModel>>
}