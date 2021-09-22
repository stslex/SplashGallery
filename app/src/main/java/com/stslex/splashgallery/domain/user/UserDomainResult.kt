package com.stslex.splashgallery.domain.user

sealed interface UserDomainResult {
    fun <T> map(mapper: UserDomainMapper<T>): T

    class Success(val data: UserDomain) : UserDomainResult {
        override fun <T> map(mapper: UserDomainMapper<T>): T = mapper.map(data)
    }

    class Failure(val exception: String) : UserDomainResult {
        override fun <T> map(mapper: UserDomainMapper<T>): T = mapper.map(exception)
    }
}