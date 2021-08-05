package com.stslex.splashgallery.ui.photo_details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.stslex.splashgallery.data.model.domain.image.ImageModel
import com.stslex.splashgallery.data.repository.interf.PhotoRepository
import com.stslex.splashgallery.utils.Result
import kotlinx.coroutines.launch
import javax.inject.Inject

class PhotoDetailsViewModel @Inject constructor(private val repository: PhotoRepository) :
    ViewModel() {
    private var _currentPhoto = MutableLiveData<Result<ImageModel>>()
    val currentPhoto: LiveData<Result<ImageModel>> get() = _currentPhoto

    fun getCurrentPhoto(id: String) {
        viewModelScope.launch {
            _currentPhoto.value = repository.getCurrentPhoto(id)
        }
    }
}