package com.stslex.splashgallery.core_ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.stslex.splashgallery.core.Resource
import com.stslex.splashgallery.core_coroutines.AppDispatcher
import kotlinx.coroutines.flow.*

abstract class BaseViewModel(
    private val appDispatcher: AppDispatcher
) : ViewModel() {

    val <T> Flow<Resource<T>>.getReformat: StateFlow<Resource<T>>
        get() = flowOn(appDispatcher.io)
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.Lazily,
                initialValue = Resource.Loading
            )
}