package com.stslex.splashgallery.ui.user

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.stslex.splashgallery.core.Abstract
import com.stslex.splashgallery.data.model.user.RemoteUserModel
import com.stslex.splashgallery.data.user.UserRepository
import com.stslex.splashgallery.ui.core.UIResponse
import com.stslex.splashgallery.ui.core.UIResult
import com.stslex.splashgallery.ui.model.user.UserModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@ExperimentalCoroutinesApi
class UserViewModel @Inject constructor(
    private val repository: UserRepository,
    private val mapper: Abstract.Mapper.DataToUI<RemoteUserModel, UIResult<UserModel>>,
    private val response: UIResponse
) : ViewModel() {

    suspend fun getUserInfo(username: String): StateFlow<UIResult<UserModel>> =
        response.getAndMap(repository.getUser(username), mapper).stateIn(
            scope = viewModelScope,
            started = SharingStarted.Lazily,
            initialValue = UIResult.Loading
        )
}