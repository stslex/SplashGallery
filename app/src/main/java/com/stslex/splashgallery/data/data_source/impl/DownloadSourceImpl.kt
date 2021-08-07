package com.stslex.splashgallery.data.data_source.impl

import com.stslex.splashgallery.data.data_source.interf.DownloadSource
import com.stslex.splashgallery.data.model.domain.DownloadModel
import com.stslex.splashgallery.data.model.remote.RemoteDownloadModel
import com.stslex.splashgallery.data.service.DownloadService
import com.stslex.splashgallery.mapper.DownloadMapper
import com.stslex.splashgallery.utils.API_KEY_SUCCESS
import com.stslex.splashgallery.utils.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class DownloadSourceImpl @Inject constructor(private val service: DownloadService) :
    DownloadSource {
    override suspend fun downloadPhoto(id: String): Result<DownloadModel> =
        withContext(Dispatchers.IO) {
            return@withContext try {
                val result = service.downloadPhoto(id = id, api_key = API_KEY_SUCCESS)
                if (result.isSuccessful && result.body() != null) {
                    val mapper = DownloadMapper()
                    val remoteUrl = result.body() as RemoteDownloadModel
                    val url = mapper.transformToDomain(remoteUrl)
                    Result.Success(url)
                } else {
                    Result.Failure("Null result")
                }
            } catch (exception: Exception) {
                Result.Failure(exception.toString())
            }
        }
}