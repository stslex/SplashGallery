package com.stslex.splashgallery.domain.photos

import com.stslex.splashgallery.domain.photo.PhotoDomain

sealed interface PhotosDomainResult {

    fun <T> map(mapper: PhotosDomainMapper<T>): T

    data class Success(private val data: List<PhotoDomain>) : PhotosDomainResult {
        override fun <T> map(mapper: PhotosDomainMapper<T>): T = mapper.map(data)
    }

    class Failure(private val exception: String) : PhotosDomainResult {
        override fun <T> map(mapper: PhotosDomainMapper<T>): T = mapper.map(exception)
    }
}