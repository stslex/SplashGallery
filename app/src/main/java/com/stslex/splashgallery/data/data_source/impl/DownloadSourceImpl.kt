package com.stslex.splashgallery.data.data_source.impl

import com.stslex.splashgallery.data.data_source.interf.DownloadSource
import com.stslex.splashgallery.data.service.DownloadService
import com.stslex.splashgallery.utils.API_KEY_SUCCESS
import javax.inject.Inject

class DownloadSourceImpl @Inject constructor(private val service: DownloadService) :
    DownloadSource {
    override suspend fun downloadPhoto(id: String): String = service.downloadPhoto(
        id = id,
        api_key = API_KEY_SUCCESS
    ).body().toString()
}