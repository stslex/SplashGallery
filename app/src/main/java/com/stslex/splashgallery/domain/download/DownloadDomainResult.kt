package com.stslex.splashgallery.domain.download

sealed interface DownloadDomainResult {

    fun <T> map(mapper: DownloadDomainMapper<T>): T

    class Success(private val data: DownloadDomain) : DownloadDomainResult {
        override fun <T> map(mapper: DownloadDomainMapper<T>): T = mapper.map(data)
    }

    class Failure(private val exception: Exception) : DownloadDomainResult {
        override fun <T> map(mapper: DownloadDomainMapper<T>): T = mapper.map(exception)
    }
}