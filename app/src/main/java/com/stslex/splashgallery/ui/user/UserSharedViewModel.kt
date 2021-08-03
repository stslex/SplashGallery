package com.stslex.splashgallery.ui.user

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.stslex.splashgallery.data.model.domain.collection.CollectionModel
import com.stslex.splashgallery.data.model.domain.image.ImageModel

class UserSharedViewModel : ViewModel() {

    private var _photos = MutableLiveData<List<ImageModel>>()
    val photos: LiveData<List<ImageModel>> get() = _photos
    fun setPhotos(photos: List<ImageModel>) {
        _photos.value = photos
    }

    private var _likes = MutableLiveData<List<ImageModel>>()
    val likes: LiveData<List<ImageModel>> get() = _likes
    fun setLikes(likes: List<ImageModel>) {
        _likes.value = likes
    }

    private var _collections = MutableLiveData<List<CollectionModel>>()
    val collections: LiveData<List<CollectionModel>> get() = _collections
    fun setCollections(collections: List<CollectionModel>) {
        _collections.value = collections
    }

    private var _numPhotos = MutableLiveData<Int>()
    val numPhotos: LiveData<Int> get() = _numPhotos
    fun setNumPhotos(number: Int) {
        _numPhotos.value = number
    }

    private var _numLikes = MutableLiveData<Int>()
    val numLikes: LiveData<Int> get() = _numLikes
    fun setNumLikes(number: Int) {
        _numLikes.value = number
    }

    private var _numCollections = MutableLiveData<Int>()
    val numCollections: LiveData<Int> get() = _numCollections
    fun setNumCollections(number: Int) {
        _numCollections.value = number
    }
}