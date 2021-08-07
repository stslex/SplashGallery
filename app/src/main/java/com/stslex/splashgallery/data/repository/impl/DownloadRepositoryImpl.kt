package com.stslex.splashgallery.data.repository.impl

import com.stslex.splashgallery.data.data_source.interf.DownloadSource
import com.stslex.splashgallery.data.repository.interf.DownloadRepository
import javax.inject.Inject

class DownloadRepositoryImpl @Inject constructor(private val source: DownloadSource) :
    DownloadRepository {
    override suspend fun downloadPhoto(id: String) = source.downloadPhoto(id)
}