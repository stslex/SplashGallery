package com.stslex.splashgallery.ui.main_screen

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.stslex.splashgallery.data.model.PagesModel
import com.stslex.splashgallery.data.repository.Repository
import com.stslex.splashgallery.mapper.PagesMapper
import com.stslex.splashgallery.utils.Result
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch

class MainViewModel(private val repository: Repository, ioDispatcher: CoroutineDispatcher) :
    ViewModel() {

    private val _image = MutableLiveData<PagesModel>()
    val image get() = _image

    fun getImage() {
        viewModelScope.launch {
            val mapper = PagesMapper()
            when (val result = repository.getPageFromRetrofit()) {
                is Result.Success -> {
                    _image.value = mapper.transformToDomain(result.data)
                }
            }

        }
    }


}