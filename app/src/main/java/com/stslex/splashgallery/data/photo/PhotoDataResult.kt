package com.stslex.splashgallery.data.photo

import com.stslex.splashgallery.data.photos.PhotoData

sealed class PhotoDataResult {
    abstract fun <T> map(mapper: PhotoDataMapper<T>): T
    data class Success(val data: PhotoData) : PhotoDataResult() {
        override fun <T> map(mapper: PhotoDataMapper<T>): T = mapper.map(data)
    }

    class Failure(val exception: String) : PhotoDataResult() {
        override fun <T> map(mapper: PhotoDataMapper<T>): T = mapper.map(exception)
    }
}
