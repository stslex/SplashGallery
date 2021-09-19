package com.stslex.splashgallery.ui.core

sealed class UIResult<out R> {
    class Success<T>(val data: T) : UIResult<T>()
    class Failure(val exception: String) : UIResult<Nothing>()
    object Loading : UIResult<Nothing>()
}