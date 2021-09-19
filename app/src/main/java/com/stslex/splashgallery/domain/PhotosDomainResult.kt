package com.stslex.splashgallery.domain

sealed interface PhotosDomainResult {
    fun <T> map(mapper: PhotosDomainMapper<T>): T
    class Success(val data: List<PhotosDomain>) : PhotosDomainResult {
        override fun <T> map(mapper: PhotosDomainMapper<T>): T = mapper.map(data)
    }

    class Failure(private val exception: Exception) : PhotosDomainResult {
        override fun <T> map(mapper: PhotosDomainMapper<T>): T = mapper.map(exception)
    }
}