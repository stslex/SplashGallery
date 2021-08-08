package com.stslex.splashgallery.ui.user

import androidx.lifecycle.LiveData
import com.stslex.splashgallery.data.model.domain.image.ImageModel
import com.stslex.splashgallery.utils.base.BaseSharedPhotosViewModel

class UserPhotosSharedViewModel : BaseSharedPhotosViewModel() {
    override val photos: LiveData<List<ImageModel>>
        get() = super.photos
    override val numberPhotos: LiveData<Int>
        get() = super.numberPhotos

}