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
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val photoRepository: PhotoRepository,
    private val collectionRepository: CollectionRepository
) :
    ViewModel() {

    private val _allPhotos = MutableLiveData<Result<List<ImageModel>>>()
    val allPhotos: LiveData<Result<List<ImageModel>>> get() = _allPhotos

    private val _allCollections = MutableLiveData<Result<List<CollectionModel>>>()
    val allCollections: LiveData<Result<List<CollectionModel>>> get() = _allCollections

    fun getAllPhotos(pageNumber: Int) {
        viewModelScope.launch {
            _allPhotos.value = photoRepository.getAllPhotos(pageNumber)
        }
    }

    fun getAllCollections(pageNumber: Int) {
        viewModelScope.launch {
            _allCollections.value = collectionRepository.getAllCollections(pageNumber)
        }
    }

}