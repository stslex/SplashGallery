package com.stslex.splashgallery.data.user

import com.stslex.splashgallery.core.Abstract
import com.stslex.splashgallery.data.model.user.RemoteUserModel
import com.stslex.splashgallery.data.toUserModel
import com.stslex.splashgallery.ui.user.UserUIResult
import javax.inject.Inject

interface UserDataMapper<T> : Abstract.Mapper.DataToUI<RemoteUserModel, T> {

    class Base @Inject constructor() : UserDataMapper<UserUIResult> {
        override fun map(data: RemoteUserModel): UserUIResult =
            UserUIResult.Success(data.toUserModel())

        override fun map(exception: Exception): UserUIResult =
            UserUIResult.Failure(exception = exception)

    }
}