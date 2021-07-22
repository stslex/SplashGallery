package com.stslex.splashgallery.ui.main_screen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.stslex.splashgallery.data.model.PagesModel
import com.stslex.splashgallery.data.model.title.TopicsModel
import com.stslex.splashgallery.data.repository.Repository
import com.stslex.splashgallery.utils.Result
import kotlinx.coroutines.launch

class MainViewModel(private val repository: Repository) :
    ViewModel() {

    private val _allPhotos = MutableLiveData<Result<PagesModel>>()
    val allPhotos: LiveData<Result<PagesModel>> get() = _allPhotos

    private val _allTopics = MutableLiveData<Result<List<TopicsModel>>>()
    val allTopics get() = _allTopics

    private val _singleTopic = MutableLiveData<Result<PagesModel>>()
    val singleTopic get() = _singleTopic

    fun getAllPhotos(pageNumber: Int) {
        viewModelScope.launch {
            val result = repository.getAllPhotos(pageNumber)
            _allPhotos.value = result
        }
    }

    fun getTopics() {
        viewModelScope.launch {
            val result = repository.getTopics()
            allTopics.value = result
        }
    }

    fun getSingleTopic(t_id: String, pageNumber: Int) {
        viewModelScope.launch {
            val result = repository.getSingleTopic(t_id, pageNumber)
            singleTopic.value = result
        }
    }
}