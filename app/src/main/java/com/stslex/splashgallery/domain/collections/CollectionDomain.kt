package com.stslex.splashgallery.domain.collections

interface CollectionDomain {

    fun id(): String
    fun title(): String
    fun url(): String
    fun number(): String
    fun username(): String
    fun avatar(): String

    data class Base(
        private val id: String,
        private val title: String,
        private val url: String,
        private val number: String,
        private val username: String,
        private val avatar: String
    ) : CollectionDomain {

        override fun id(): String = id
        override fun title(): String = title
        override fun url(): String = url
        override fun number(): String = number
        override fun username(): String = username
        override fun avatar(): String = avatar
    }
}