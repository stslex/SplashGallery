package com.stslex.splashgallery.ui.user

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.stslex.splashgallery.data.model.domain.user.UserModel
import com.stslex.splashgallery.data.repository.ImageRepository
import com.stslex.splashgallery.utils.Result
import kotlinx.coroutines.launch
import javax.inject.Inject

class UserViewModel @Inject constructor(private val repository: ImageRepository) :
    ViewModel() {

    private val _user = MutableLiveData<Result<UserModel>>()
    val user: LiveData<Result<UserModel>> get() = _user

    fun getUserInfo(username: String) {
        viewModelScope.launch {
            _user.value = repository.getUserInfo(username)
        }
    }
}