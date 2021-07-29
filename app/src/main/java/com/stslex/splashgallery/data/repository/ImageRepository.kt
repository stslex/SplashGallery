package com.stslex.splashgallery.data.repository

import com.stslex.splashgallery.data.model.domain.PagesCollectionModel
import com.stslex.splashgallery.data.model.domain.PagesModel
import com.stslex.splashgallery.data.model.domain.image.ImageModel
import com.stslex.splashgallery.utils.Result

interface ImageRepository {
    suspend fun getAllPhotos(pageNumber: Int): Result<PagesModel>
    suspend fun getAllCollections(pageNumber: Int): Result<PagesCollectionModel>
    suspend fun getCollectionPhotos(id: String, pageNumber: Int): Result<PagesModel>
    suspend fun getCurrentPhoto(id: String): Result<ImageModel>
}