package com.stslex.splashgallery.data.repository.interf

interface DownloadRepository {
    suspend fun downloadPhoto(id: String): String
}