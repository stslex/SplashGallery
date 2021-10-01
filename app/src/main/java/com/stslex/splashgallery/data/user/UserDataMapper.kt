package com.stslex.splashgallery.data.user

import com.stslex.splashgallery.core.Abstract
import com.stslex.splashgallery.data.model.user.RemoteUserModel
import com.stslex.splashgallery.data.toUserModel
import com.stslex.splashgallery.ui.core.UIResult
import com.stslex.splashgallery.ui.model.user.UserModel
import javax.inject.Inject

class UserDataMapper @Inject constructor() :
    Abstract.Mapper.DataToUI<RemoteUserModel, UIResult<UserModel>> {

    override fun map(data: RemoteUserModel): UIResult<UserModel> =
        UIResult.Success(data.toUserModel())

    override fun map(exception: Exception): UIResult<UserModel> =
        UIResult.Failure(exception = exception)
}