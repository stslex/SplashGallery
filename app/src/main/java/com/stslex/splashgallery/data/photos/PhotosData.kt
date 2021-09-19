package com.stslex.splashgallery.data.photos

interface PhotosData {

    fun imageId(): String
    fun imageUrl(): String
    fun userId(): String
    fun userName(): String
    fun userUrl(): String

    data class Base(
        val id: String = "",
        val urls: UrlsData = UrlsData(),
        val user: UserData = UserData(),
        val profile_image: ProfileImageData = ProfileImageData()
    ) : PhotosData {
        data class UrlsData(
            val regular: String = ""
        )

        data class UserData(
            val id: String = "",
            val username: String = ""
        )

        data class ProfileImageData(
            val medium: String = ""
        )

        override fun imageId(): String = id
        override fun imageUrl(): String = urls.regular
        override fun userId(): String = user.id
        override fun userName(): String = user.username
        override fun userUrl(): String = profile_image.medium
    }


}