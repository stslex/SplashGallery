package com.stslex.splashgallery.data.repository.impl

import com.stslex.splashgallery.data.core.CreateResponse
import com.stslex.splashgallery.data.model.domain.image.ImageModel
import com.stslex.splashgallery.data.model.remote.RemoteImageModel
import com.stslex.splashgallery.data.repository.interf.PhotoRepository
import com.stslex.splashgallery.data.service.PhotoService
import com.stslex.splashgallery.utils.API_KEY_SUCCESS
import com.stslex.splashgallery.utils.Result
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@ExperimentalCoroutinesApi
class PhotoRepositoryImpl @Inject constructor(
    private val service: PhotoService,
    private val response: CreateResponse<RemoteImageModel, ImageModel>
) : PhotoRepository {

    override suspend fun getCurrentPhoto(id: String): Flow<Result<ImageModel>> =
        response.createSingle(service.getCurrentPhoto(id, API_KEY_SUCCESS))

}