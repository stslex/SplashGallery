package com.stslex.splashgallery.domain

import com.stslex.splashgallery.data.core.DataResult
import com.stslex.splashgallery.data.photos.PhotosData
import com.stslex.splashgallery.data.photos.PhotosDataMapper
import com.stslex.splashgallery.data.photos.PhotosRepository
import com.stslex.splashgallery.domain.core.DomainResult
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.trySendBlocking
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.collect
import javax.inject.Inject

@ExperimentalCoroutinesApi
interface PhotosInteractor<T> {

    suspend fun getAllPhotos(page: Int): Flow<DomainResult<T>>
    suspend fun getUserPhotos(username: String, page: Int): Flow<DomainResult<T>>
    suspend fun getUserLikes(username: String, page: Int): Flow<DomainResult<T>>
    suspend fun getCollectionPhotos(id: String, page: Int): Flow<DomainResult<T>>

    class Base @Inject constructor(
        private val repository: PhotosRepository,
        private val mapper: PhotosDataMapper<DomainResult<List<PhotosDomain>>>
    ) : PhotosInteractor<List<PhotosDomain>> {

        override suspend fun getAllPhotos(page: Int) = repository.getAllPhotos(page).collect()

        override suspend fun getUserPhotos(username: String, page: Int) =
            repository.getUserPhotos(username, page).collect()

        override suspend fun getUserLikes(username: String, page: Int) =
            repository.getUserLikes(username, page).collect()

        override suspend fun getCollectionPhotos(id: String, page: Int) =
            repository.getCollectionPhotos(id, page).collect()

        private suspend fun Flow<DataResult<List<PhotosData>>>.collect(): Flow<DomainResult<List<PhotosDomain>>> =
            callbackFlow {
                try {
                    this@collect.collect {
                        trySendBlocking(it.map(mapper))
                    }
                } catch (exception: Exception) {
                    trySendBlocking(DomainResult.Failure(exception.toString()))
                }
            }

    }
}