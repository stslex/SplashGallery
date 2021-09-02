package com.stslex.splashgallery.ui.all_photos

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.stslex.splashgallery.data.model.domain.image.ImageModel
import com.stslex.splashgallery.data.repository.interf.PhotoRepository
import com.stslex.splashgallery.utils.Result
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

class AllPhotosViewModel @Inject constructor(
    private val photoRepository: PhotoRepository
) : ViewModel() {

    suspend fun getAllPhotos(pageNumber: Int): StateFlow<Result<List<ImageModel>>> =
        photoRepository.getAllPhotos(pageNumber).stateIn(
            scope = viewModelScope,
            started = SharingStarted.Lazily,
            initialValue = Result.Loading
        )
}