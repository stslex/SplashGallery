package com.stslex.splashgallery.data.user

import com.stslex.splashgallery.data.model.user.RemoteUserModel

sealed interface UserDataResult {

    fun <T> map(mapper: UserDataMapper<T>): T
    class Success(private val data: RemoteUserModel) : UserDataResult {
        override fun <T> map(mapper: UserDataMapper<T>): T = mapper.map(data)
    }

    class Failure(private val exception: Exception) : UserDataResult {
        override fun <T> map(mapper: UserDataMapper<T>): T = mapper.map(exception)
    }
}