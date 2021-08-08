package com.stslex.splashgallery.ui.single_collection

import androidx.lifecycle.LiveData
import com.stslex.splashgallery.data.model.domain.image.ImageModel
import com.stslex.splashgallery.utils.base.BaseSharedPhotosViewModel

class SingleCollectionSharedViewModel : BaseSharedPhotosViewModel() {
    override val photos: LiveData<List<ImageModel>>
        get() = super.photos
    override val numberPhotos: LiveData<Int>
        get() = super.numberPhotos
}