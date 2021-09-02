package com.stslex.splashgallery.data.repository.interf

import com.stslex.splashgallery.data.model.domain.image.ImageModel
import com.stslex.splashgallery.utils.Result
import kotlinx.coroutines.flow.Flow

interface AllPhotosRepository {
    suspend fun getAllPhotos(page: Int): Flow<Result<List<ImageModel>>>
    suspend fun getUserPhotos(username: String, page: Int): Flow<Result<List<ImageModel>>>
    suspend fun getUserLikes(username: String, page: Int): Flow<Result<List<ImageModel>>>
    suspend fun getCollectionPhotos(id: String, page: Int): Flow<Result<List<ImageModel>>>
}