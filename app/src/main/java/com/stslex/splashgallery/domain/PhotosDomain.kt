package com.stslex.splashgallery.domain

interface PhotosDomain {

    fun imageId(): String
    fun imageUrl(): String
    fun userId(): String
    fun userName(): String
    fun userUrl(): String

    data class Base(
        private val imageId: String,
        private val imageUrl: String,
        private val userId: String,
        private val userName: String,
        private val userUrl: String
    ) : PhotosDomain {

        override fun imageId(): String = imageId
        override fun imageUrl(): String = imageUrl
        override fun userId(): String = imageId
        override fun userName(): String = userName
        override fun userUrl(): String = userUrl
    }
}