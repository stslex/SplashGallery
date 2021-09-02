package com.stslex.splashgallery.data.repository.interf

import com.stslex.splashgallery.data.model.domain.image.ImageModel
import com.stslex.splashgallery.utils.Result

interface PhotoRepository {
    suspend fun getCurrentPhoto(id: String): Result<ImageModel>
}