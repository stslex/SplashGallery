package com.stslex.splashgallery.utils

sealed class Result<out R> {
    data class Success<out T>(val data: T) : Result<T>()
    class Failure(val exception: String) : Result<Nothing>()
    object Loading : Result<Nothing>()

    override fun toString(): String {
        return when (this) {
            is Success<*> -> "Success[data=$data]"
            is Failure -> "Failure[exception=$exception]"
            is Loading -> "Loading"
        }
    }
}