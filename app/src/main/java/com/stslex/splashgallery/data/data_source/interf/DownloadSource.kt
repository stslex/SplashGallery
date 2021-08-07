package com.stslex.splashgallery.data.data_source.interf

import com.stslex.splashgallery.data.model.domain.DownloadModel
import com.stslex.splashgallery.utils.Result

interface DownloadSource {
    suspend fun downloadPhoto(id: String): Result<DownloadModel>
}