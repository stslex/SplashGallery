package com.stslex.splashgallery.data.photo

import com.stslex.splashgallery.core.Abstract

sealed interface DataResult<D> {
    fun <U> map(mapper: Abstract.Mapper.DataToUI<D, U>): U
    class Success<T>(val data: T) : DataResult<T> {
        override fun <U> map(mapper: Abstract.Mapper.DataToUI<T, U>) = mapper.map(data)
    }

    class Failure<T>(val exception: Exception) : DataResult<T> {
        override fun <U> map(mapper: Abstract.Mapper.DataToUI<T, U>): U =
            mapper.map(exception)
    }
}
