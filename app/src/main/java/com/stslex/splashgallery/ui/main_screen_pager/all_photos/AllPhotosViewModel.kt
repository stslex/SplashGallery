package com.stslex.splashgallery.ui.main_screen_pager.all_photos

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.stslex.splashgallery.data.model.domain.PagesModel

class AllPhotosViewModel : ViewModel() {
    private val _allPhotos = MutableLiveData<PagesModel>()
    val allPhotos: LiveData<PagesModel> get() = _allPhotos
    fun setAllPhotos(page: PagesModel) {
        _allPhotos.value = page
    }

    private val _pageNumberAllPhotos = MutableLiveData<Int>()
    val pageNumberAllPhotos: LiveData<Int> get() = _pageNumberAllPhotos
    fun setPageNumberAppPhotos(number: Int) {
        _pageNumberAllPhotos.value = number
    }


}