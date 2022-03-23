package com.stslex.core

sealed class Resource<out T> : Object<T> {

    data class Success<T>(val data: T) : Resource<T>() {
        override fun <U> map(mapper: Mapper.DataToUI<in T, U>): U = mapper.map(data)
    }

    data class Failure<T>(val exception: Exception) : Resource<T>() {
        override fun <U> map(mapper: Mapper.DataToUI<in T, U>): U =
            mapper.map(exception = exception)
    }

    object Loading : Resource<Nothing>() {
        override fun <U> map(mapper: Mapper.DataToUI<in Nothing, U>): U = mapper.map()
    }
}