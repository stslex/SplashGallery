package com.stslex.splashgallery.core_ui

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class SharedViewModel : ViewModel() {

    private val _currentId: MutableStateFlow<String> = MutableStateFlow(INITIAL_VALUE)
    val currentId: StateFlow<String>
        get() = _currentId

    fun setId(id: String) {
        _currentId.tryEmit(id)
    }

    companion object {
        private const val INITIAL_VALUE: String = ""
    }
}