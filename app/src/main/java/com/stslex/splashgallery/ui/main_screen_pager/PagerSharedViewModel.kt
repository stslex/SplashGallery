package com.stslex.splashgallery.ui.main_screen_pager

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.stslex.splashgallery.data.model.PagesModel

class PagerSharedViewModel : ViewModel() {
    val page = MutableLiveData<PagesModel>()
}