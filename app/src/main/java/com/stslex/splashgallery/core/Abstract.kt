package com.stslex.splashgallery.core

interface Abstract {

    interface Object<T, M : Mapper> {

        fun map(mapper: M): T
    }

    interface UiObject {
        class Empty : UiObject
    }

    interface Mapper {

        interface Data<S, R> : Mapper {
            fun map(data: S): R
        }

        interface DataToUI<S, R> : Data<S, R> {
            fun map(exception: Exception): R
        }

        class Empty : Mapper
    }
}