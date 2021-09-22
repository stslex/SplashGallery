package com.stslex.splashgallery.ui.detail_photo

interface DownloadUI {

    fun startDownload(function: (String) -> Unit)

    data class Base(
        private val url: String
    ) : DownloadUI {

        override fun startDownload(function: (String) -> Unit) = function(url)

    }
}