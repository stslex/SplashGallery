package com.stslex.splashgallery.data.model.domain.image

data class ExifModel(
    val make: String?,
    val model: String?,
    val exposure_time: String?,
    val aperture: String?,
    val focal_length: String?,
    val iso: Int?
)