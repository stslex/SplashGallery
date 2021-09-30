package com.stslex.splashgallery.ui.detail_photo

sealed class DownloadUIResult {
    class Success(val url: DownloadUI) : DownloadUIResult()
    class Failure(val exception: Exception) : DownloadUIResult()
    object Loading : DownloadUIResult()
}