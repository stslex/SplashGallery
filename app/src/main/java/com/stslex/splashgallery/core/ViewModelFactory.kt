package com.stslex.splashgallery.core

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import javax.inject.Inject
import javax.inject.Provider

class ViewModelFactory @Inject constructor(
    private val viewModelMap: Map<Class<out ViewModel>, @JvmSuppressWildcards Provider<ViewModel>>
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        @Suppress("UNCHECKED_CAST")
        modelClass.checkedViewModel?.let { return it.get() as T }
        throw IllegalArgumentException("Unknown model class $modelClass")
    }

    private val <T : ViewModel> Class<T>.checkedViewModel: Provider<ViewModel>?
        get() = viewModelMap[this] ?: checkViewModelAssignable()

    private fun <T> Class<T>.checkViewModelAssignable(): Provider<ViewModel>? {
        viewModelMap.forEach {
            if (isAssignableFrom(it.key)) return it.value
        }
        return null
    }
}