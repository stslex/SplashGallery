package com.stslex.splashgallery.domain.photo

interface PhotoDomain {

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
        private val imageId: String,
        private val imageUrl: String,
        private val userId: String,
        private val userName: String,
        private val userUrl: String,
        private val exif: ExifDomain
    ) : PhotoDomain {
        data class ExifDomain(
            val make: String,
            val model: String,
            val exposure_time: String,
            val aperture: String,
            val focal_length: String,
            val iso: String
        )

        override fun imageId(): String = imageId
        override fun imageUrl(): String = imageUrl
        override fun userId(): String = imageId
        override fun userName(): String = userName
        override fun userUrl(): String = userUrl

        override fun make(): String = exif.make
        override fun model(): String = exif.model
        override fun exposureTime(): String = exif.exposure_time
        override fun aperture(): String = exif.aperture
        override fun focalLength(): String = exif.focal_length
        override fun iso(): String = exif.iso
    }
}