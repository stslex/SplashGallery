package com.stslex.splashgallery.data.photos

sealed class PhotosDataResult {
    abstract fun <T> map(mapper: PhotosDataMapper<T>): T
    data class Success(val data: List<PhotosData>) : PhotosDataResult() {
        override fun <T> map(mapper: PhotosDataMapper<T>): T = mapper.map(data)
    }

    class Failure(val exception: String) : PhotosDataResult() {
        override fun <T> map(mapper: PhotosDataMapper<T>): T = mapper.map(exception)
    }
}