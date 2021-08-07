package com.stslex.splashgallery.data.repository.interf

import com.stslex.splashgallery.data.model.domain.DownloadModel
import com.stslex.splashgallery.utils.Result

interface DownloadRepository {
    suspend fun downloadPhoto(id: String): Result<DownloadModel>
}