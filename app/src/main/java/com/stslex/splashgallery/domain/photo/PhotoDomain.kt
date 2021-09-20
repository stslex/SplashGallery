package com.stslex.splashgallery.domain.photo

interface PhotoDomain {

    fun imageId(): String
    fun imageUrl(): String
    fun userId(): String
    fun userName(): String
    fun userUrl(): String
    fun getExifDomain(): Base.ExifDomain

    data class Base(
        private val imageId: String,
        private val imageUrl: String,
        private val userId: String,
        private val userName: String,
        private val userUrl: String,
        private val exif: ExifDomain.Base
    ) : PhotoDomain {

        override fun imageId(): String = imageId
        override fun imageUrl(): String = imageUrl
        override fun userId(): String = imageId
        override fun userName(): String = userName
        override fun userUrl(): String = userUrl
        override fun getExifDomain(): ExifDomain = exif

        interface ExifDomain {
            fun make(): String
            fun model(): String
            fun exposureTime(): String
            fun aperture(): String
            fun focalLength(): String
            fun iso(): String

            data class Base(
                private val make: String,
                private val model: String,
                private val exposure_time: String,
                private val aperture: String,
                private val focal_length: String,
                private val iso: String
            ) : ExifDomain {
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