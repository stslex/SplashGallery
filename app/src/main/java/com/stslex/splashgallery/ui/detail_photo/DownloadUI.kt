package com.stslex.splashgallery.ui.detail_photo

interface DownloadUI {

    data class Base(
        private val url: String
    ) : DownloadUI
}