package com.stslex.splashgallery.data.photo

interface PhotoData {

    fun imageId(): String
    fun imageUrl(): String
    fun userId(): String
    fun userName(): String
    fun userUrl(): String

    fun make(): String
    fun model(): String
    fun exposureTime(): String
    fun aperture(): String
    fun focalLength(): String
    fun iso(): String

    data class Base(
        val id: String = "",
        val urls: UrlsData = UrlsData(),
        val user: UserData = UserData(),
        val exif: ExifData = ExifData()
    ) : PhotoData {
        data class UrlsData(
            val regular: String = ""
        )

        data class UserData(
            val id: String = "",
            val username: String = "",
            val profile_image: ProfileImageData = ProfileImageData(),
        )

        data class ProfileImageData(
            val medium: String = ""
        )

        data class ExifData(
            val make: String = "",
            val model: String = "",
            val exposure_time: String = "",
            val aperture: String = "",
            val focal_length: String = "",
            val iso: String = ""
        )

        override fun imageId(): String = id
        override fun imageUrl(): String = urls.regular
        override fun userId(): String = user.id
        override fun userName(): String = user.username
        override fun userUrl(): String = user.profile_image.medium

        override fun make(): String = exif.make
        override fun model(): String = exif.model
        override fun exposureTime(): String = exif.exposure_time
        override fun aperture(): String = exif.aperture
        override fun focalLength(): String = exif.focal_length
        override fun iso(): String = exif.iso
    }


}