package com.stslex.splashgallery.utils

import androidx.fragment.app.Fragment
import com.stslex.splashgallery.data.data_source.RemoteSource
import com.stslex.splashgallery.data.repository.Repository
import com.stslex.splashgallery.ui.main_screen.MainViewModelFactory

open class BaseFragment : Fragment() {
    private val remoteSource = RemoteSource()
    private val repository = Repository(remoteSource)
    open val viewModelFactory = MainViewModelFactory(repository)
}