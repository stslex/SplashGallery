package com.stslex.splashgallery.domain.photos

import com.stslex.splashgallery.data.photos.PhotosRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@ExperimentalCoroutinesApi
interface PhotosInteractor {

    suspend fun getAllPhotos(page: Int): Flow<PhotosDomainResult>
    suspend fun getUserPhotos(username: String, page: Int): Flow<PhotosDomainResult>
    suspend fun getUserLikes(username: String, page: Int): Flow<PhotosDomainResult>
    suspend fun getCollectionPhotos(id: String, page: Int): Flow<PhotosDomainResult>

    class Base @Inject constructor(
        private val repository: PhotosRepository,
        private val response: PhotosDomainResponse
    ) : PhotosInteractor {

        override suspend fun getAllPhotos(page: Int) =
            response.create(repository.getAllPhotos(page))

        override suspend fun getUserPhotos(username: String, page: Int) =
            response.create(repository.getUserPhotos(username, page))

        override suspend fun getUserLikes(username: String, page: Int) =
            response.create(repository.getUserLikes(username, page))

        override suspend fun getCollectionPhotos(id: String, page: Int) =
            response.create(repository.getCollectionPhotos(id, page))

    }
}