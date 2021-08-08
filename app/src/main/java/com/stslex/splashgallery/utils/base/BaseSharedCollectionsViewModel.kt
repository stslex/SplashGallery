package com.stslex.splashgallery.utils.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.stslex.splashgallery.data.model.domain.collection.CollectionModel

abstract class BaseSharedCollectionsViewModel : ViewModel() {
    private val _collections = MutableLiveData<List<CollectionModel>>()
    open val collection: LiveData<List<CollectionModel>> get() = _collections
    open fun setCollection(collection: List<CollectionModel>) {
        _collections.value = collection
    }

    private val _numberCollections = MutableLiveData<Int>()
    open val numberCollections: LiveData<Int> get() = _numberCollections
    open fun setNumberCollections(number: Int) {
        _numberCollections.value = number
    }
}