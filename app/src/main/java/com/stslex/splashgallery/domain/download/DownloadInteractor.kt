package com.stslex.splashgallery.domain.download

import com.stslex.splashgallery.data.download.DownloadRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@ExperimentalCoroutinesApi
interface DownloadInteractor {

    suspend fun downloadPhoto(id: String): Flow<DownloadDomainResult>

    class Base @Inject constructor(
        private val repository: DownloadRepository,
        private val response: DownloadDomainResponse
    ) : DownloadInteractor {

        override suspend fun downloadPhoto(id: String): Flow<DownloadDomainResult> =
            response.create(repository.downloadPhoto(id))
    }

}