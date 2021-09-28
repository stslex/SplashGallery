package com.stslex.splashgallery.data.photo

import com.stslex.splashgallery.data.photos.PhotoData

sealed interface PhotoDataResult {
    fun <T> map(mapper: PhotoDataMapper<T>): T
    data class Success(private val data: PhotoData) : PhotoDataResult {
        override fun <T> map(mapper: PhotoDataMapper<T>): T = mapper.map(data)
    }

    class Failure(private val exception: String) : PhotoDataResult {
        override fun <T> map(mapper: PhotoDataMapper<T>): T = mapper.map(exception)
    }
}
