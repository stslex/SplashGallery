package com.stslex.splashgallery.core

interface Mapper {

    interface Data<D, U> : Mapper {
        fun map(data: D): U
    }

    interface DataToUI<D, U> : Data<D, U> {
        fun map(exception: Exception): U
        fun map(): U
    }
}