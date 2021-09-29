package com.stslex.splashgallery.ui.activity

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import javax.inject.Inject

class MainActivityViewModel @Inject constructor() : ViewModel() {

    private val _currentId = Channel<String>()
    val currentId = _currentId.receiveAsFlow()

    suspend fun postId(event: String) {
        _currentId.send(event)
    }
}