package com.stslex.splashgallery

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.stslex.splashgallery.data.repository.ImageRepository
import com.stslex.splashgallery.ui.main_screen.MainViewModel
import javax.inject.Inject

class ViewModelFactory @Inject constructor(private val repository: ImageRepository) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return MainViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}