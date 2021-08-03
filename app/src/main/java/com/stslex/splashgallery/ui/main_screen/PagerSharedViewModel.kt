package com.stslex.splashgallery.ui.main_screen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.stslex.splashgallery.data.model.domain.collection.CollectionModel
import com.stslex.splashgallery.data.model.domain.image.ImageModel

class PagerSharedViewModel : ViewModel() {

    private val _allPhotos = MutableLiveData<List<ImageModel>>()
    val allPhotos: LiveData<List<ImageModel>> get() = _allPhotos
    fun setAllPhotos(page: List<ImageModel>) {
        _allPhotos.value = page
    }

    private val _collections = MutableLiveData<List<CollectionModel>>()
    val collection: LiveData<List<CollectionModel>> get() = _collections
    fun setCollection(collection: List<CollectionModel>) {
        _collections.value = collection
    }

    private val _pageNumberAllPhotos = MutableLiveData<Int>()
    val pageNumberAllPhotos: LiveData<Int> get() = _pageNumberAllPhotos
    fun setPageNumberAppPhotos(number: Int) {
        _pageNumberAllPhotos.value = number
    }

    private val _pageNumberCollections = MutableLiveData<Int>()
    val pageNumberCollections: LiveData<Int> get() = _pageNumberCollections
    fun setPageNumberCollections(number: Int) {
        _pageNumberCollections.value = number
    }

}