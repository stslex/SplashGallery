package com.stslex.splashgallery.data.data_source

import com.stslex.splashgallery.data.model.domain.PagesCollectionModel
import com.stslex.splashgallery.data.model.domain.PagesModel
import com.stslex.splashgallery.utils.Result

interface RemoteSource {
    suspend fun getAllPhotos(pageNumber: Int): Result<PagesModel>
    suspend fun getAllCollections(pageNumber: Int): Result<PagesCollectionModel>
    suspend fun getCollectionPhotos(id: String, pageNumber: Int): Result<PagesModel>
}