package com.stslex.splashgallery.mapper

interface BaseMapper<E, D> {
    fun transformToDomain(type: E): D
}