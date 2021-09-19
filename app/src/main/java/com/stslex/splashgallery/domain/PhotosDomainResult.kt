package com.stslex.splashgallery.domain

sealed interface PhotosDomainResult {
    fun <T> map(mapper: PhotosDomainMapper): T
    class Success(val data: List<PhotosDomain>) : PhotosDomainResult {
        override fun <T> map(mapper: PhotosDomainMapper): T = mapper.map(data) //TODO
    }

    class Failure(val exception: Exception) : PhotosDomainResult {
        override fun <T> map(mapper: PhotosDomainMapper): T = mapper.map(exception) //TODO
    }
}