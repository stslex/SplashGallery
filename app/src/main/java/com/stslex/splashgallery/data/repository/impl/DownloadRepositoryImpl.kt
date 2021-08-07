package com.stslex.splashgallery.data.repository.impl

import com.stslex.splashgallery.data.data_source.interf.DownloadSource
import com.stslex.splashgallery.data.model.domain.DownloadModel
import com.stslex.splashgallery.data.repository.interf.DownloadRepository
import com.stslex.splashgallery.utils.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class DownloadRepositoryImpl @Inject constructor(private val source: DownloadSource) :
    DownloadRepository {
    override suspend fun downloadPhoto(id: String): Result<DownloadModel> =
        withContext(Dispatchers.IO) {
            return@withContext try {
                when (val response = source.downloadPhoto(id)) {
                    is Result.Success -> {
                        Result.Success(response.data)
                    }
                    is Result.Failure -> {
                        Result.Failure(response.exception)
                    }
                    is Result.Loading -> {
                        Result.Loading
                    }
                }
            } catch (exception: Exception) {
                Result.Failure(exception = exception.toString())
            }
        }
}