package com.stslex.splashgallery.data.data_source.interf

import com.stslex.splashgallery.data.model.domain.image.ImageModel
import com.stslex.splashgallery.utils.Result

interface PhotoSource {
    suspend fun getAllPhotos(pageNumber: Int): Result<List<ImageModel>>
    suspend fun getCurrentPhoto(id: String): Result<ImageModel>
}