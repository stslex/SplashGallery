package com.stslex.splashgallery.domain

sealed class PhotosDomainResult {
    abstract fun <T> map(mapper: PhotosDomainMapper<T>): T

    data class Success(val data: List<PhotosDomain>) : PhotosDomainResult() {
        override fun <T> map(mapper: PhotosDomainMapper<T>): T = mapper.map(data)
    }

    class Failure(val exception: String) : PhotosDomainResult() {
        override fun <T> map(mapper: PhotosDomainMapper<T>): T = mapper.map(exception)
    }
}