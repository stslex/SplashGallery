package com.stslex.splashgallery.domain.core

import com.stslex.splashgallery.core.Abstract

sealed interface DomainResult<out R> {
    fun <T, F> map(mapper: Abstract.Mapper.DomainToUi<F, T>): T

    data class Success<S>(val data: S) : DomainResult<S> {
        override fun <T, F> map(mapper: Abstract.Mapper.DomainToUi<F, T>): T =
            mapper.map(data as F)
    }

    class Failure(val exception: String) : DomainResult<Nothing> {
        override fun <T, F> map(mapper: Abstract.Mapper.DomainToUi<F, T>): T =
            mapper.map(exception)
    }
}