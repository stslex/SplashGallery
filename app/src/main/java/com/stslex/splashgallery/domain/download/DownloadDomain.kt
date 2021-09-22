package com.stslex.splashgallery.domain.download

interface DownloadDomain {

    fun url(): String

    data class Base(
        private val url: String
    ) : DownloadDomain {

        override fun url(): String = url
    }
}