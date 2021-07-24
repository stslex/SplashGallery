package com.stslex.splashgallery.di.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.stslex.splashgallery.ViewModelFactory
import com.stslex.splashgallery.di.key.ViewModelKey
import com.stslex.splashgallery.ui.main_screen.MainViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    abstract fun bindHomeFragmentViewModel(viewModel: MainViewModel): ViewModel
}