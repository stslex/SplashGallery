package com.stslex.splashgallery.data.user

interface UserData {

    fun username(): String
    fun url(): String
    fun totalCollections(): String
    fun totalPhotos(): String
    fun totalLikes(): String
    fun bio(): String

    data class Base(
        val username: String = "",
        val profile_image: ProfileImageData,
        val total_collections: String = "",
        val total_likes: String = "",
        val total_photos: String = "",
        val bio: String?
    ) : UserData {

        data class ProfileImageData(
            val large: String = ""
        )

        override fun username(): String = username
        override fun url(): String = profile_image.large
        override fun totalCollections(): String = total_collections
        override fun totalPhotos(): String = total_photos
        override fun totalLikes(): String = total_likes
        override fun bio(): String = bio ?: ""
    }
}