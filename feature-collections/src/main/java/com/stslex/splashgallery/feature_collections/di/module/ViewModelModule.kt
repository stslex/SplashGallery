package com.stslex.splashgallery.feature_collections.di.module

import androidx.lifecycle.ViewModel
import com.stslex.splashgallery.feature_collections.di.key.ViewModelKey
import com.stslex.splashgallery.feature_collections.ui.CollectionViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface ViewModelModule {

    @IntoMap
    @Binds
    @ViewModelKey(CollectionViewModel::class)
    fun bindsCollectionViewModel(viewModel: CollectionViewModel): ViewModel
}