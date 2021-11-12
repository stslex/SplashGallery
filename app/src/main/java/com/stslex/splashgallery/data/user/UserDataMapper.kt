package com.stslex.splashgallery.data.user

import com.stslex.splashgallery.core.Mapper
import com.stslex.splashgallery.core.Resource
import com.stslex.splashgallery.data.core.toUserModel
import com.stslex.splashgallery.data.model.user.RemoteUserModel
import com.stslex.splashgallery.ui.model.user.UserModel
import javax.inject.Inject

interface UserDataMapper : Mapper.DataToUI<RemoteUserModel, Resource<UserModel>> {

    class Base @Inject constructor() : UserDataMapper {

        override fun map(data: RemoteUserModel): Resource<UserModel> =
            Resource.Success(data.toUserModel())

        override fun map(exception: Exception): Resource<UserModel> =
            Resource.Failure(exception = exception)

        override fun map(): Resource<UserModel> = Resource.Loading
    }
}