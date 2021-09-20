package com.stslex.splashgallery.data.photos

interface PhotoData {

    fun imageId(): String
    fun imageUrl(): String
    fun userId(): String
    fun userName(): String
    fun userUrl(): String
    fun getExifData(): Base.ExifData

    data class Base(
        val id: String = "",
        val urls: UrlsData = UrlsData(),
        val user: UserData = UserData(),
        val profile_image: ProfileImageData = ProfileImageData(),
        val exif: ExifData = ExifData.Base()
    ) : PhotoData {
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
        override fun getExifData(): ExifData = exif

        interface ExifData {
            fun make(): String
            fun model(): String
            fun exposureTime(): String
            fun aperture(): String
            fun focalLength(): String
            fun iso(): String

            data class Base(
                val make: String = "",
                val model: String = "",
                val exposure_time: String = "",
                val aperture: String = "",
                val focal_length: String = "",
                val iso: String = ""
            ) : ExifData {
                override fun make(): String = make
                override fun model(): String = model
                override fun exposureTime(): String = exposure_time
                override fun aperture(): String = aperture
                override fun focalLength(): String = focal_length
                override fun iso(): String = iso
            }
        }
    }


}