package com.stslex.splashgallery.di.module

import androidx.lifecycle.ViewModelProvider
import com.stslex.splashgallery.ui.core.ViewModelFactory
import dagger.Binds
import dagger.Module

@Module
interface ViewModelFactoryModule {
    @Binds
    fun bindsViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory
}