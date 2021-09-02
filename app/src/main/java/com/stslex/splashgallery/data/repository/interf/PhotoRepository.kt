package com.stslex.splashgallery.data.repository.interf

import com.stslex.splashgallery.data.model.domain.image.ImageModel
import com.stslex.splashgallery.utils.Result
import kotlinx.coroutines.flow.Flow

interface PhotoRepository {
    suspend fun getAllPhotos(pageNumber: Int): Flow<Result<List<ImageModel>>>
    suspend fun getCurrentPhoto(id: String): Result<ImageModel>
}