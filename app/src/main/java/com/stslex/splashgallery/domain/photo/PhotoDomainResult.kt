package com.stslex.splashgallery.domain.photo

sealed interface PhotoDomainResult {

    fun <T> map(mapper: PhotoDomainMapper<T>): T

    data class Success(private val data: PhotoDomain) : PhotoDomainResult {
        override fun <T> map(mapper: PhotoDomainMapper<T>): T = mapper.map(data)
    }

    class Failure(private val exception: Exception) : PhotoDomainResult {
        override fun <T> map(mapper: PhotoDomainMapper<T>): T = mapper.map(exception)
    }
}