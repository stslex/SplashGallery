package com.stslex.splashgallery.ui.main_screen

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.stslex.splashgallery.data.model.PagesModel
import com.stslex.splashgallery.data.repository.Repository
import com.stslex.splashgallery.utils.Result
import kotlinx.coroutines.launch

class MainViewModel(private val repository: Repository) :
    ViewModel() {

    private val _page = MutableLiveData<Result<PagesModel>>()
    val page get() = _page

    fun getImage() {
        viewModelScope.launch {
            val result = repository.getPageFromRetrofit()
            page.value = result
        }
    }
}