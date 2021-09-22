package com.stslex.splashgallery.domain.user

interface UserDomain {

    fun username(): String
    fun url(): String
    fun totalCollections(): String
    fun totalPhotos(): String
    fun totalLikes(): String
    fun bio(): String

    data class Base(
        private val username: String,
        private val url: String,
        private val totalCollections: String,
        private val totalPhotos: String,
        private val totalLikes: String,
        private val bio: String
    ) : UserDomain {

        override fun username(): String = username
        override fun url(): String = url
        override fun totalCollections(): String = totalCollections
        override fun totalPhotos(): String = totalPhotos
        override fun totalLikes(): String = totalLikes
        override fun bio(): String = bio
    }
}