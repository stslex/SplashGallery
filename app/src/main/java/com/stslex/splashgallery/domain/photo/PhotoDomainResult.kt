package com.stslex.splashgallery.domain.photo

sealed class PhotoDomainResult {
    abstract fun <T> map(mapper: PhotoDomainMapper<T>): T

    data class Success(val data: PhotoDomain) : PhotoDomainResult() {
        override fun <T> map(mapper: PhotoDomainMapper<T>): T = mapper.map(data)
    }

    class Failure(val exception: String) : PhotoDomainResult() {
        override fun <T> map(mapper: PhotoDomainMapper<T>): T = mapper.map(exception)
    }
}