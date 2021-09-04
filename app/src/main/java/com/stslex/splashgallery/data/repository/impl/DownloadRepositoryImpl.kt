package com.stslex.splashgallery.data.repository.impl

import com.stslex.splashgallery.data.base.interf.CreateResponse
import com.stslex.splashgallery.data.model.domain.DownloadModel
import com.stslex.splashgallery.data.model.remote.RemoteDownloadModel
import com.stslex.splashgallery.data.repository.interf.DownloadRepository
import com.stslex.splashgallery.data.service.DownloadService
import com.stslex.splashgallery.utils.API_KEY_SUCCESS
import com.stslex.splashgallery.utils.Result
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@ExperimentalCoroutinesApi
class DownloadRepositoryImpl @Inject constructor(
    private val service: DownloadService,
    private val response: CreateResponse<RemoteDownloadModel, DownloadModel>
) : DownloadRepository {

    override suspend fun downloadPhoto(id: String): Flow<Result<DownloadModel>> =
        response.createSingle(service.downloadPhoto(id = id, api_key = API_KEY_SUCCESS))

}