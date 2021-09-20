package com.stslex.splashgallery.domain.photo

import com.stslex.splashgallery.data.photo.PhotoRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@ExperimentalCoroutinesApi
interface PhotoInteractor {

    suspend fun getCurrentPhoto(id: String): Flow<PhotoDomainResult>
    class Base @Inject constructor(
        private val repository: PhotoRepository,
        private val response: PhotoDomainResponse
    ) : PhotoInteractor {
        override suspend fun getCurrentPhoto(id: String): Flow<PhotoDomainResult> =
            response.create(repository.getCurrentPhoto(id))
    }
}