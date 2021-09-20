package com.stslex.splashgallery.data.photos

import com.stslex.splashgallery.utils.API_KEY_SUCCESS
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@ExperimentalCoroutinesApi
interface PhotosRepository {
    suspend fun getAllPhotos(page: Int): Flow<PhotosDataResult>
    suspend fun getUserPhotos(username: String, page: Int): Flow<PhotosDataResult>
    suspend fun getUserLikes(username: String, page: Int): Flow<PhotosDataResult>
    suspend fun getCollectionPhotos(id: String, page: Int): Flow<PhotosDataResult>

    class Base @Inject constructor(
        private val service: AllPhotosService,
        private val response: PhotosDataResponse
    ) : PhotosRepository {

        override suspend fun getAllPhotos(page: Int): Flow<PhotosDataResult> =
            response.create(service.getAllPhotos(page, API_KEY_SUCCESS))

        override suspend fun getUserPhotos(username: String, page: Int): Flow<PhotosDataResult> =
            response.create(service.getUserPhotos(username, page, API_KEY_SUCCESS))

        override suspend fun getUserLikes(username: String, page: Int): Flow<PhotosDataResult> =
            response.create(service.getUserLikes(username, page, API_KEY_SUCCESS))

        override suspend fun getCollectionPhotos(id: String, page: Int): Flow<PhotosDataResult> =
            response.create(service.getCollectionPhotos(id, page, API_KEY_SUCCESS))

    }


}