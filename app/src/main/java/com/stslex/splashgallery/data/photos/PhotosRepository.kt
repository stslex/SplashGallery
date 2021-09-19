package com.stslex.splashgallery.data.photos

import com.stslex.splashgallery.data.core.CreateResponse
import com.stslex.splashgallery.data.model.domain.image.ImageModel
import com.stslex.splashgallery.data.model.remote.RemoteImageModel
import com.stslex.splashgallery.data.service.AllPhotosService
import com.stslex.splashgallery.utils.API_KEY_SUCCESS
import com.stslex.splashgallery.utils.Result
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@ExperimentalCoroutinesApi
interface PhotosRepository {
    suspend fun getAllPhotos(page: Int): Flow<Result<List<ImageModel>>>
    suspend fun getUserPhotos(username: String, page: Int): Flow<Result<List<ImageModel>>>
    suspend fun getUserLikes(username: String, page: Int): Flow<Result<List<ImageModel>>>
    suspend fun getCollectionPhotos(id: String, page: Int): Flow<Result<List<ImageModel>>>

    class Base @Inject constructor(
        private val service: AllPhotosService,
        private val response: CreateResponse<RemoteImageModel, ImageModel>
    ) : PhotosRepository {

        override suspend fun getAllPhotos(page: Int): Flow<Result<List<ImageModel>>> =
            response.create(service.getAllPhotos(page, API_KEY_SUCCESS))

        override suspend fun getUserPhotos(
            username: String,
            page: Int
        ): Flow<Result<List<ImageModel>>> =
            response.create(service.getUserPhotos(username, page, API_KEY_SUCCESS))

        override suspend fun getUserLikes(
            username: String,
            page: Int
        ): Flow<Result<List<ImageModel>>> =
            response.create(service.getUserLikes(username, page, API_KEY_SUCCESS))

        override suspend fun getCollectionPhotos(
            id: String,
            page: Int
        ): Flow<Result<List<ImageModel>>> =
            response.create(service.getCollectionPhotos(id, page, API_KEY_SUCCESS))
    }
}