package com.stslex.splashgallery.data.download

sealed interface DownloadDataResult {

    fun <T> map(mapper: DownloadDataMapper<T>): T

    class Success(val data: DownloadData) : DownloadDataResult {
        override fun <T> map(mapper: DownloadDataMapper<T>): T = mapper.map(data)
    }

    class Failure(val exception: String) : DownloadDataResult {
        override fun <T> map(mapper: DownloadDataMapper<T>): T = mapper.map(exception)
    }
}