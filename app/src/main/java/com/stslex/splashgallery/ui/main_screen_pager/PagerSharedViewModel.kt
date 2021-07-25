package com.stslex.splashgallery.ui.main_screen_pager

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.stslex.splashgallery.data.model.domain.PagesCollectionModel
import com.stslex.splashgallery.data.model.domain.PagesModel
import javax.inject.Inject

class PagerSharedViewModel @Inject constructor() : ViewModel() {
    private val _page = MutableLiveData<PagesModel>()
    val page: LiveData<PagesModel> get() = _page
    fun setPage(page: PagesModel) {
        _page.value = page
    }

    private val _collections = MutableLiveData<PagesCollectionModel>()
    val collection: LiveData<PagesCollectionModel> get() = _collections
    fun setCollection(collection: PagesCollectionModel) {
        _collections.value = collection
    }

    private val _pageNumber = MutableLiveData<Int>()
    val pageNumber: LiveData<Int> get() = _pageNumber
    fun setPageNumber(number: Int) {
        _pageNumber.value = number
    }
}