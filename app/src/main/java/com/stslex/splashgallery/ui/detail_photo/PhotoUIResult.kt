package com.stslex.splashgallery.ui.detail_photo

import com.stslex.splashgallery.ui.photos.PhotosUI

sealed class PhotoUIResult {
    class Success(val data: PhotosUI) : PhotoUIResult()
    class Failure(val exception: String) : PhotoUIResult()
    object Loading : PhotoUIResult()
}