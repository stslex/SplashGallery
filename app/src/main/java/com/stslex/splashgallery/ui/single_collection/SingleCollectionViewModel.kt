package com.stslex.splashgallery.ui.single_collection

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.stslex.splashgallery.data.model.domain.image.ImageModel
import com.stslex.splashgallery.data.repository.interf.CollectionRepository
import com.stslex.splashgallery.utils.Result
import kotlinx.coroutines.launch
import javax.inject.Inject

class SingleCollectionViewModel @Inject constructor(private val repository: CollectionRepository) :
    ViewModel() {

    private val _allPhotos = MutableLiveData<Result<List<ImageModel>>>()
    val allPhotos: LiveData<Result<List<ImageModel>>> get() = _allPhotos

    fun getAllPhotos(id: String, pageNumber: Int) {
        viewModelScope.launch {
            _allPhotos.value = repository.getCollectionPhotos(id, pageNumber)
        }
    }
}