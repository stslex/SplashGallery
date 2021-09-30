package com.stslex.splashgallery.ui.user

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.stslex.splashgallery.data.user.UserRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@ExperimentalCoroutinesApi
class UserViewModel @Inject constructor(
    private val repository: UserRepository,
    private val response: UserUIResponse
) : ViewModel() {

    suspend fun getUserInfo(username: String): StateFlow<UserUIResult> =
        response.create(repository.getUser(username)).stateIn(
            scope = viewModelScope,
            started = SharingStarted.Lazily,
            initialValue = UserUIResult.Loading
        )
}