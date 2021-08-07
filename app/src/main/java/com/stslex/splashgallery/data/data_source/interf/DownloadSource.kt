package com.stslex.splashgallery.data.data_source.interf

interface DownloadSource {
    suspend fun downloadPhoto(id: String): String
}