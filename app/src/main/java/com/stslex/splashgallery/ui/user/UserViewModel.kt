package com.stslex.splashgallery.ui.user

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.stslex.splashgallery.data.model.domain.collection.CollectionModel
import com.stslex.splashgallery.data.model.domain.image.ImageModel
import com.stslex.splashgallery.data.model.domain.user.UserModel
import com.stslex.splashgallery.data.repository.interf.UserRepository
import com.stslex.splashgallery.utils.Result
import kotlinx.coroutines.launch
import javax.inject.Inject

class UserViewModel @Inject constructor(private val repository: UserRepository) :
    ViewModel() {

    private val _user = MutableLiveData<Result<UserModel>>()
    val user: LiveData<Result<UserModel>> get() = _user

    private val _likes = MutableLiveData<Result<List<ImageModel>>>()
    val likes: LiveData<Result<List<ImageModel>>> get() = _likes

    private val _photos = MutableLiveData<Result<List<ImageModel>>>()
    val photos: LiveData<Result<List<ImageModel>>> get() = _photos

    private val _collections = MutableLiveData<Result<List<CollectionModel>>>()
    val collections: LiveData<Result<List<CollectionModel>>> get() = _collections

    fun getUserInfo(username: String) {
        viewModelScope.launch {
            _user.value = repository.getUserInfo(username)
        }
    }

    fun getUserContentCollections(username: String, page: Int) {
        viewModelScope.launch {
            _collections.value = repository.getUserContentCollections(username, page)
        }
    }


}