package com.stslex.splashgallery.domain.photos

import com.stslex.splashgallery.domain.photo.PhotoDomain

sealed class PhotosDomainResult {
    abstract fun <T> map(mapper: PhotosDomainMapper<T>): T

    data class Success(val data: List<PhotoDomain>) : PhotosDomainResult() {
        override fun <T> map(mapper: PhotosDomainMapper<T>): T = mapper.map(data)
    }

    class Failure(val exception: String) : PhotosDomainResult() {
        override fun <T> map(mapper: PhotosDomainMapper<T>): T = mapper.map(exception)
    }
}