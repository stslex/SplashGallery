package com.stslex.splashgallery.ui.user.pager_view_models

import androidx.lifecycle.LiveData
import com.stslex.splashgallery.data.model.domain.image.ImageModel
import com.stslex.splashgallery.utils.base.BaseSharedPhotosViewModel

class UserLikesSharedViewModel : BaseSharedPhotosViewModel() {
    override val photos: LiveData<List<ImageModel>>
        get() = super.photos
    override val numberPhotos: LiveData<Int>
        get() = super.numberPhotos
}