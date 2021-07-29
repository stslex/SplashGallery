package com.stslex.splashgallery.di.module

import androidx.lifecycle.ViewModel
import com.stslex.splashgallery.di.key.ViewModelKey
import com.stslex.splashgallery.ui.main_screen.MainViewModel
import com.stslex.splashgallery.ui.single_collection.SingleCollectionViewModel
import com.stslex.splashgallery.ui.single_photo_screen.SinglePhotoViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface ViewModelModule {
    @IntoMap
    @Binds
    @ViewModelKey(MainViewModel::class)
    fun bindsMainViewModel(viewModel: MainViewModel): ViewModel

    @IntoMap
    @Binds
    @ViewModelKey(SingleCollectionViewModel::class)
    fun bindsSingleCollectionViewModel(viewModel: SingleCollectionViewModel): ViewModel

    @IntoMap
    @Binds
    @ViewModelKey(SinglePhotoViewModel::class)
    fun bindsSinglePhotoViewModel(viewModel: SinglePhotoViewModel): ViewModel
}