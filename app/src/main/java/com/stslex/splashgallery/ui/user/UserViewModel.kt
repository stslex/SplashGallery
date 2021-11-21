package com.stslex.splashgallery.ui.user

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.stslex.splashgallery.core.Resource
import com.stslex.splashgallery.data.model.ui.user.UserModel
import com.stslex.splashgallery.data.user.UserDataMapper
import com.stslex.splashgallery.data.user.UserRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@ExperimentalCoroutinesApi
class UserViewModel @Inject constructor(
    private val repository: UserRepository,
    private val mapper: UserDataMapper
) : ViewModel() {

    suspend fun getUserInfo(username: String): StateFlow<Resource<UserModel>> =
        repository.getUser(username).flatMapLatest {
            flowOf(it.map(mapper))
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.Lazily,
            initialValue = Resource.Loading
        )
}