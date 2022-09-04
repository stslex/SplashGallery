package com.stslex.splashgallery.ui.model.topics

import com.stslex.splashgallery.ui.model.image.UrlsModel

data class PreviewPhotosModel(
    val id: String,
    val created_at: String,
    val updated_at: String,
    val urls: UrlsModel
)