package com.stslex.splashgallery.data.model.ui.topics

import com.stslex.splashgallery.data.model.ui.image.UrlsModel

data class PreviewPhotosModel(
    val id: String,
    val created_at: String,
    val updated_at: String,
    val urls: UrlsModel
)