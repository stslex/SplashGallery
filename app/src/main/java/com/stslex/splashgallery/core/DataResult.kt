package com.stslex.splashgallery.core

sealed class DataResult<R> {
    abstract fun <T> map(mapper: Abstract.Mapper.DataToDomain<R, T>): T

    data class Success<S>(val data: S) : DataResult<S>() {
        override fun <T> map(mapper: Abstract.Mapper.DataToDomain<S, T>): T =
            mapper.map(data)
    }

    class Failure(val exception: String) : DataResult<Nothing>() {
        override fun <T> map(mapper: Abstract.Mapper.DataToDomain<Nothing, T>): T =
            mapper.map(exception)
    }
}