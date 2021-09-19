package com.stslex.splashgallery.domain

import com.stslex.splashgallery.data.photos.PhotosData
import com.stslex.splashgallery.data.photos.PhotosRepository
import com.stslex.splashgallery.domain.core.DomainResponse
import com.stslex.splashgallery.domain.core.DomainResult
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@ExperimentalCoroutinesApi
interface PhotosInteractor<T> {

    suspend fun getAllPhotos(page: Int): Flow<DomainResult<T>>
    suspend fun getUserPhotos(username: String, page: Int): Flow<DomainResult<T>>
    suspend fun getUserLikes(username: String, page: Int): Flow<DomainResult<T>>
    suspend fun getCollectionPhotos(id: String, page: Int): Flow<DomainResult<T>>

    class Base @Inject constructor(
        private val repository: PhotosRepository,
        private val response: DomainResponse<List<PhotosData>, DomainResult<List<PhotosDomain>>>
    ) : PhotosInteractor<List<PhotosDomain>> {

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