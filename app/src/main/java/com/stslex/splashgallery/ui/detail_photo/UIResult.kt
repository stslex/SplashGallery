package com.stslex.splashgallery.ui.detail_photo

sealed class UIResult<out T> {
    class Success<S>(val data: S) : UIResult<S>()
    class Failure(val exception: Exception) : UIResult<Nothing>()
    object Loading : UIResult<Nothing>()
}
