package com.stslex.splashgallery.utils

import androidx.fragment.app.Fragment
import com.stslex.splashgallery.di.DaggerAppComponent

open class BaseFragment : Fragment() {
    open val viewModelFactory = DaggerAppComponent.create().viewModelFactory
}