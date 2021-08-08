package com.stslex.splashgallery.utils.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.stslex.splashgallery.data.model.domain.image.ImageModel

open class BaseSharedPhotosViewModel : ViewModel() {
    private val _photos = MutableLiveData<List<ImageModel>>()
    open val photos: LiveData<List<ImageModel>> get() = _photos
    open fun setPhotos(page: List<ImageModel>) {
        _photos.value = page
    }

    private val _numberPhotos = MutableLiveData<Int>()
    open val numberPhotos: LiveData<Int> get() = _numberPhotos
    open fun setNumberPhotos(number: Int) {
        _numberPhotos.value = number
    }
}