package com.stslex.splashgallery.data.download

interface DownloadData {

    fun url(): String

    data class Base(
        val url: String = ""
    ) : DownloadData {
        override fun url(): String = url
    }
}