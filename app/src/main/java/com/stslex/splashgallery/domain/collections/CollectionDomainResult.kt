package com.stslex.splashgallery.domain.collections

sealed interface CollectionDomainResult {

    fun <T> map(mapper: CollectionDomainMapper<T>): T
    class Success(private val data: List<CollectionDomain>) : CollectionDomainResult {
        override fun <T> map(mapper: CollectionDomainMapper<T>): T = mapper.map(data)
    }

    class Failure(private val exception: String) : CollectionDomainResult {
        override fun <T> map(mapper: CollectionDomainMapper<T>): T = mapper.map(exception)
    }
}