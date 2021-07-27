package com.stslex.splashgallery.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.stslex.splashgallery.data.model.domain.PagesCollectionModel
import com.stslex.splashgallery.data.model.domain.PagesModel

class PagerSharedViewModel : ViewModel() {
    private val _allPhotos = MutableLiveData<PagesModel>()
    val allPhotos: LiveData<PagesModel> get() = _allPhotos
    fun setAllPhotos(page: PagesModel) {
        _allPhotos.value = page
    }

    private val _collections = MutableLiveData<PagesCollectionModel>()
    val collection: LiveData<PagesCollectionModel> get() = _collections
    fun setCollection(collection: PagesCollectionModel) {
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