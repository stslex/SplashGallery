package com.stslex.splashgallery.data.repository.interf

import com.stslex.splashgallery.data.model.domain.DownloadModel
import com.stslex.splashgallery.utils.Result
import kotlinx.coroutines.flow.Flow

interface DownloadRepository {
    suspend fun downloadPhoto(id: String): Flow<Result<DownloadModel>>
}