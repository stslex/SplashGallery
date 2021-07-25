package com.stslex.splashgallery.ui.base

import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import javax.inject.Inject

open class BaseFragment : Fragment() {

    @Inject
    open lateinit var viewModelFactory: ViewModelProvider.Factory
}