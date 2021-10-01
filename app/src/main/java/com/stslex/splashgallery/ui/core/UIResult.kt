package com.stslex.splashgallery.ui.core

sealed class UIResult<out T> {
    class Success<S>(val data: S) : UIResult<S>()
    class Failure(val exception: Exception) : UIResult<Nothing>()
    object Loading : UIResult<Nothing>()
}
