package com.stslex.splashgallery.ui.all_photos

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.stslex.splashgallery.data.model.domain.image.ImageModel
import com.stslex.splashgallery.data.repository.interf.AllPhotosRepository
import com.stslex.splashgallery.utils.Result
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

class AllPhotosViewModel @Inject constructor(
    private val repository: AllPhotosRepository
) : ViewModel() {

    suspend fun getAllPhotos(page: Int): StateFlow<Result<List<ImageModel>>> =
        repository.getAllPhotos(page).stateIn(
            scope = viewModelScope,
            started = SharingStarted.Lazily,
            initialValue = Result.Loading
        )

    suspend fun getUserPhotos(
        username: String,
        page: Int
    ): StateFlow<Result<List<ImageModel>>> =
        repository.getUserPhotos(username, page).stateIn(
            scope = viewModelScope,
            started = SharingStarted.Lazily,
            initialValue = Result.Loading
        )

    suspend fun getUserLikes(
        username: String,
        page: Int
    ): StateFlow<Result<List<ImageModel>>> =
        repository.getUserLikes(username, page).stateIn(
            scope = viewModelScope,
            started = SharingStarted.Lazily,
            initialValue = Result.Loading
        )

    suspend fun getCollectionPhotos(
        id: String,
        page: Int
    ): StateFlow<Result<List<ImageModel>>> =
        repository.getCollectionPhotos(id, page).stateIn(
            scope = viewModelScope,
            started = SharingStarted.Lazily,
            initialValue = Result.Loading
        )

}