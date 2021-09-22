package com.stslex.splashgallery

import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow

class BroadcastEvent {

    //TODO Try this
    private val _currentId = MutableSharedFlow<String>()
    val currentId = _currentId.asSharedFlow()

    suspend fun setCurrentId(id: String) {
        _currentId.emit(id)
    }
}