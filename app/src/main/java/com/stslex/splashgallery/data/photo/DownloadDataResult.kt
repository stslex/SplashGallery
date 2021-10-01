package com.stslex.splashgallery.data.photo

import com.stslex.splashgallery.data.model.download.RemoteDownloadModel

sealed interface DownloadDataResult {

    fun <T> map(mapper: DownloadDataMapper<T>): T

    class Success(private val data: RemoteDownloadModel) : DownloadDataResult {
        override fun <T> map(mapper: DownloadDataMapper<T>): T = mapper.map(data)
    }

    class Failure(private val exception: Exception) : DownloadDataResult {
        override fun <T> map(mapper: DownloadDataMapper<T>): T = mapper.map(exception)
    }
}