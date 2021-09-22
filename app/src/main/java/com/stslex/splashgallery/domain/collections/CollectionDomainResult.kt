package com.stslex.splashgallery.domain.collections

sealed interface CollectionDomainResult {

    fun <T> map(mapper: CollectionDomainMapper<T>): T
    class Success(val data: List<CollectionDomain>) : CollectionDomainResult {
        override fun <T> map(mapper: CollectionDomainMapper<T>): T = mapper.map(data)
    }

    class Failure(val exception: String) : CollectionDomainResult {
        override fun <T> map(mapper: CollectionDomainMapper<T>): T = mapper.map(exception)
    }
}