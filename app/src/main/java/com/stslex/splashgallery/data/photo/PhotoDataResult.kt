package com.stslex.splashgallery.data.photo

import com.stslex.splashgallery.data.model.image.RemoteImageModel

sealed interface PhotoDataResult {

    fun <T> map(mapper: PhotoDataMapper<T>): T

    data class Success(private val data: RemoteImageModel) : PhotoDataResult {
        override fun <T> map(mapper: PhotoDataMapper<T>): T = mapper.map(data)
    }

    class Failure(private val exception: Exception) : PhotoDataResult {
        override fun <T> map(mapper: PhotoDataMapper<T>): T = mapper.map(exception)
    }
}
