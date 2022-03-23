package com.stslex.splashgallery.data.user

import com.stslex.splashgallery.data.core.map
import com.stslex.splashgallery.data.model.ui.user.UserModel
import st.slex.csplashscreen.data.model.remote.user.RemoteUserModel
import javax.inject.Inject

interface UserDataMapper :
    com.stslex.core.Mapper.DataToUI<RemoteUserModel, com.stslex.core.Resource<UserModel>> {

    class Base @Inject constructor() : UserDataMapper {

        override fun map(data: RemoteUserModel): com.stslex.core.Resource<UserModel> =
            com.stslex.core.Resource.Success(data.map())

        override fun map(exception: Exception): com.stslex.core.Resource<UserModel> =
            com.stslex.core.Resource.Failure(exception = exception)

        override fun map(): com.stslex.core.Resource<UserModel> = com.stslex.core.Resource.Loading
    }
}