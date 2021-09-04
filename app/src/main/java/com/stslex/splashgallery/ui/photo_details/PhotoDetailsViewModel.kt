package com.stslex.splashgallery.ui.photo_details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.stslex.splashgallery.data.model.domain.DownloadModel
import com.stslex.splashgallery.data.model.domain.image.ImageModel
import com.stslex.splashgallery.data.repository.interf.DownloadRepository
import com.stslex.splashgallery.data.repository.interf.PhotoRepository
import com.stslex.splashgallery.utils.Result
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

class PhotoDetailsViewModel @Inject constructor(
    private val repository: PhotoRepository,
    private val downloadRepository: DownloadRepository
) :
    ViewModel() {
    private var _currentPhoto = MutableLiveData<Result<ImageModel>>()
    val currentPhoto: LiveData<Result<ImageModel>> get() = _currentPhoto

    fun getCurrentPhoto(id: String) {
        viewModelScope.launch {
            _currentPhoto.value = repository.getCurrentPhoto(id)
        }
    }

    suspend fun downloadPhoto(id: String): StateFlow<Result<DownloadModel>> =
        downloadRepository.downloadPhoto(id).stateIn(
            scope = viewModelScope,
            started = SharingStarted.Lazily,
            initialValue = Result.Loading
        )
}