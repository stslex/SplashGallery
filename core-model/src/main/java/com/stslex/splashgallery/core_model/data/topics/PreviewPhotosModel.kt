package com.stslex.splashgallery.core_model.data.topics

import com.stslex.splashgallery.core_model.data.image.UrlsModel

data class PreviewPhotosModel(
    val id: String,
    val created_at: String,
    val updated_at: String,
    val urls: UrlsModel
)