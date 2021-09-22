package com.stslex.splashgallery.ui.user

sealed class UserUIResult {
    class Success(val data: UserUI) : UserUIResult()
    class Failure(val exception: String) : UserUIResult()
    object Loading : UserUIResult()
}
