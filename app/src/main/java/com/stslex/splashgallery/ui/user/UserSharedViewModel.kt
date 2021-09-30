package com.stslex.splashgallery.ui.user

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class UserSharedViewModel : ViewModel() {

    private val _currentId = MutableStateFlow<String>("")
    val currentId: StateFlow<String> get() = _currentId

    suspend fun setId(id: String) {
        _currentId.emit(id)
    }
}