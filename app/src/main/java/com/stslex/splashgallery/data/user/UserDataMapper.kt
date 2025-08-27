package com.stslex.splashgallery.data.user

import com.stslex.splashgallery.core.Mapper
import com.stslex.splashgallery.core.Resource
import com.stslex.splashgallery.data.core.map
import com.stslex.splashgallery.core_model.data.user.UserModel
import st.slex.csplashscreen.data.model.remote.user.RemoteUserModel
import javax.inject.Inject

interface UserDataMapper :
    Mapper.DataToUI<RemoteUserModel, Resource<UserModel>> {

    class Base @Inject constructor() : UserDataMapper {

        override fun map(data: RemoteUserModel): Resource<UserModel> =
            Resource.Success(data.map())

        override fun map(exception: Exception): Resource<UserModel> =
            Resource.Failure(exception = exception)

        override fun map(): Resource<UserModel> = Resource.Loading
    }
}