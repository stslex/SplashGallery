package com.stslex.splashgallery.data.photos

sealed interface PhotosDataResult {
    fun <T> map(mapper: PhotosDataMapper<T>): T
    data class Success(private val data: List<PhotoData>) : PhotosDataResult {
        override fun <T> map(mapper: PhotosDataMapper<T>): T = mapper.map(data)
    }

    class Failure(private val exception: String) : PhotosDataResult {
        override fun <T> map(mapper: PhotosDataMapper<T>): T = mapper.map(exception)
    }
}