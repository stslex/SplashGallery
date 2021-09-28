package com.stslex.splashgallery.data.collections

sealed interface CollectionDataResult {
    fun <T> map(mapper: CollectionDataMapper<T>): T
    class Success(private val data: List<CollectionData>) : CollectionDataResult {
        override fun <T> map(mapper: CollectionDataMapper<T>): T = mapper.map(data)
    }

    class Failure(private val exception: String) : CollectionDataResult {
        override fun <T> map(mapper: CollectionDataMapper<T>): T = mapper.map(exception)
    }
}