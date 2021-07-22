package com.stslex.splashgallery.ui.main_screen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.stslex.splashgallery.data.model.domain.PagesCollectionModel
import com.stslex.splashgallery.data.model.domain.PagesModel
import com.stslex.splashgallery.data.model.domain.title.TopicsModel
import com.stslex.splashgallery.data.repository.Repository
import com.stslex.splashgallery.utils.Result
import kotlinx.coroutines.launch

class MainViewModel(private val repository: Repository) :
    ViewModel() {

    private val _allPhotos = MutableLiveData<Result<PagesModel>>()
    val allPhotos: LiveData<Result<PagesModel>> get() = _allPhotos

    private val _allTopics = MutableLiveData<Result<List<TopicsModel>>>()
    val allTopics: LiveData<Result<List<TopicsModel>>> get() = _allTopics

    private val _singleTopic = MutableLiveData<Result<PagesModel>>()
    val singleTopic: LiveData<Result<PagesModel>> get() = _singleTopic

    private val _allCollections = MutableLiveData<Result<PagesCollectionModel>>()
    val allCollections: LiveData<Result<PagesCollectionModel>> get() = _allCollections

    fun getAllPhotos(pageNumber: Int) {
        viewModelScope.launch {
            _allPhotos.value = repository.getAllPhotos(pageNumber)
        }
    }

    fun getAllCollections(pageNumber: Int) {
        viewModelScope.launch {
            _allCollections.value = repository.getAllCollections(pageNumber)
        }
    }

    fun getTopics() {
        viewModelScope.launch {
            _allTopics.value = repository.getTopics()
        }
    }

    fun getSingleTopic(t_id: String, pageNumber: Int) {
        viewModelScope.launch {
            _singleTopic.value = repository.getSingleTopic(t_id, pageNumber)
        }
    }
}