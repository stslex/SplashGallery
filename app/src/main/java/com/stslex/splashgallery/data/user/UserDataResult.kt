package com.stslex.splashgallery.data.user

sealed interface UserDataResult {

    fun <T> map(mapper: UserDataMapper<T>): T
    class Success(val data: UserData) : UserDataResult {
        override fun <T> map(mapper: UserDataMapper<T>): T = mapper.map(data)
    }

    class Failure(val exception: String) : UserDataResult {
        override fun <T> map(mapper: UserDataMapper<T>): T = mapper.map(exception)
    }
}