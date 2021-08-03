package com.stslex.splashgallery.data.data_source.interf

import com.stslex.splashgallery.data.model.domain.collection.CollectionModel
import com.stslex.splashgallery.data.model.domain.image.ImageModel
import com.stslex.splashgallery.data.model.domain.user.UserModel
import com.stslex.splashgallery.utils.Result

interface UserSource {
    suspend fun getUserInfo(username: String): Result<UserModel>
    suspend fun getUserContentPhotos(username: String, page: Int): Result<List<ImageModel>>
    suspend fun getUserContentLikes(username: String, page: Int): Result<List<ImageModel>>
    suspend fun getUserContentCollections(
        username: String,
        page: Int
    ): Result<List<CollectionModel>>
}