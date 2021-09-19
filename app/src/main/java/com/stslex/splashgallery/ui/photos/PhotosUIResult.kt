package com.stslex.splashgallery.ui.photos

sealed interface PhotosUIResult {
    class Success(val data: List<PhotosUI>) : PhotosUIResult
    class Failure(val exception: Exception) : PhotosUIResult
    object Loading : PhotosUIResult
}