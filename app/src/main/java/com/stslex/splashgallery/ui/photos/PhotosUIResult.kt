package com.stslex.splashgallery.ui.photos

sealed class PhotosUIResult {
    class Success(val data: List<PhotosUI>) : PhotosUIResult()
    class Failure(val exception: String) : PhotosUIResult()
    object Loading : PhotosUIResult()
}