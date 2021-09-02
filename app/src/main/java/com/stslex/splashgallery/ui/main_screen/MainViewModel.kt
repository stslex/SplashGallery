package com.stslex.splashgallery.ui.main_screen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.stslex.splashgallery.data.model.domain.collection.CollectionModel
import com.stslex.splashgallery.data.model.domain.image.ImageModel
import com.stslex.splashgallery.data.repository.interf.CollectionRepository
import com.stslex.splashgallery.data.repository.interf.PhotoRepository
import com.stslex.splashgallery.utils.Result
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val photoRepository: PhotoRepository,
    private val collectionRepository: CollectionRepository
) :
    ViewModel() {

    private val _allCollections = MutableLiveData<Result<List<CollectionModel>>>()
    val allCollections: LiveData<Result<List<CollectionModel>>> get() = _allCollections

    suspend fun getAllPhotos(pageNumber: Int): StateFlow<Result<List<ImageModel>>> =
        photoRepository.getAllPhotos(pageNumber).stateIn(
            scope = viewModelScope,
            started = SharingStarted.Lazily,
            initialValue = Result.Loading
        )

    fun getAllCollections(pageNumber: Int) {
        viewModelScope.launch {
            _allCollections.value = collectionRepository.getAllCollections(pageNumber)
        }
    }

}