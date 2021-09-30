package com.stslex.splashgallery.ui.user

import com.stslex.splashgallery.ui.model.user.UserModel

sealed class UserUIResult {
    class Success(val data: UserModel) : UserUIResult()
    class Failure(val exception: Exception) : UserUIResult()
    object Loading : UserUIResult()
}
