package com.stslex.splashgallery.utils

import androidx.fragment.app.Fragment
import com.stslex.splashgallery.data.data_source.RemoteSource
import com.stslex.splashgallery.data.repository.Repository
import com.stslex.splashgallery.ui.main_screen.MainViewModelFactory
import kotlinx.coroutines.Dispatchers

open class BaseFragment : Fragment() {
    private val ioDispatcher = Dispatchers.IO
    private val remoteSource = RemoteSource(ioDispatcher)
    private val repository = Repository(remoteSource, ioDispatcher)
    open val viewModelFactory = MainViewModelFactory(repository)
}