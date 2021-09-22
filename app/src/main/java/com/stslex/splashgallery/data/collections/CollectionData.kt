package com.stslex.splashgallery.data.collections

interface CollectionData {

    fun id(): String
    fun title(): String
    fun url(): String
    fun number(): String
    fun username(): String
    fun avatar(): String

    data class Base(
        val id: String = "",
        val title: String = "",
        val total_photos: String = "",
        val cover_photo: CoverPhotoData,
        val user: UserData
    ) : CollectionData {

        data class CoverPhotoData(
            val urls: UrlsData
        )

        data class UrlsData(
            val regular: String = ""
        )

        data class UserData(
            val name: String = "",
            val profile_image: ProfileImageData
        )

        data class ProfileImageData(
            val medium: String = ""
        )

        override fun id(): String = id
        override fun title(): String = title
        override fun url(): String = cover_photo.urls.regular
        override fun number(): String = total_photos
        override fun username(): String = user.name
        override fun avatar(): String = user.profile_image.medium
    }
}