package com.stslex.splashgallery.data.core

import com.stslex.splashgallery.core.Abstract

sealed interface DataResult<out R> {
    fun <T, F> map(mapper: Abstract.Mapper.DataToDomain<F, T>): T
    data class Success<S>(val data: S) : DataResult<S> {
        override fun <T, F> map(mapper: Abstract.Mapper.DataToDomain<F, T>): T =
            mapper.map(data as F)
    }

    class Failure(val exception: String) : DataResult<Nothing> {
        override fun <T, F> map(mapper: Abstract.Mapper.DataToDomain<F, T>): T =
            mapper.map(exception)
    }
}