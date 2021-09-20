package com.stslex.splashgallery.domain.photo

import com.stslex.splashgallery.data.photo.PhotoRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow

@ExperimentalCoroutinesApi
interface PhotoInteractor {

    suspend fun getCurrentPhoto(id: String): Flow<PhotoDomainResult>
    class Base constructor(
        private val repository: PhotoRepository,
        private val response: PhotoDomainResponse
    ) : PhotoInteractor {
        override suspend fun getCurrentPhoto(id: String): Flow<PhotoDomainResult> =
            response.create(repository.getCurrentPhoto(id))
    }
}