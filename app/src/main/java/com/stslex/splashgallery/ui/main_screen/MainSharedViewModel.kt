package com.stslex.splashgallery.ui.main_screen

import androidx.lifecycle.LiveData
import com.stslex.splashgallery.data.model.domain.image.ImageModel
import com.stslex.splashgallery.utils.base.BaseSharedPhotosViewModel

class MainSharedViewModel : BaseSharedPhotosViewModel() {
    override val photos: LiveData<List<ImageModel>>
        get() = super.photos
    override val numberPhotos: LiveData<Int>
        get() = super.numberPhotos

}