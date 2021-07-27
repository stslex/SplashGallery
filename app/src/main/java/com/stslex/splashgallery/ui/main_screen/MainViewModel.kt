package com.stslex.splashgallery.ui.main_screen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.stslex.splashgallery.data.model.domain.PagesCollectionModel
import com.stslex.splashgallery.data.model.domain.PagesModel
import com.stslex.splashgallery.data.repository.ImageRepository
import com.stslex.splashgallery.utils.Result
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainViewModel @Inject constructor(private val imageRepository: ImageRepository) :
    ViewModel() {

    private val _allPhotos = MutableLiveData<Result<PagesModel>>()
    val allPhotos: LiveData<Result<PagesModel>> get() = _allPhotos

    private val _allCollections = MutableLiveData<Result<PagesCollectionModel>>()
    val allCollections: LiveData<Result<PagesCollectionModel>> get() = _allCollections

    fun getAllPhotos(pageNumber: Int) {
        viewModelScope.launch {
            _allPhotos.value = imageRepository.getAllPhotos(pageNumber)
        }
    }

    fun getAllCollections(pageNumber: Int) {
        viewModelScope.launch {
            _allCollections.value = imageRepository.getAllCollections(pageNumber)
        }
    }

}