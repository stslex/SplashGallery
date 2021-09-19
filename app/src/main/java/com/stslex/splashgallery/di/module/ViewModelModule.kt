package com.stslex.splashgallery.di.module

import androidx.lifecycle.ViewModel
import com.stslex.splashgallery.di.key.ViewModelKey
import com.stslex.splashgallery.ui.collections.CollectionViewModel
import com.stslex.splashgallery.ui.detail_photo.PhotoDetailsViewModel
import com.stslex.splashgallery.ui.photos.AllPhotosViewModel
import com.stslex.splashgallery.ui.user.UserViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface ViewModelModule {
    @IntoMap
    @Binds
    @ViewModelKey(PhotoDetailsViewModel::class)
    fun bindsPhotoDetailsViewModel(viewModel: PhotoDetailsViewModel): ViewModel

    @IntoMap
    @Binds
    @ViewModelKey(UserViewModel::class)
    fun bindsUserViewModel(viewModel: UserViewModel): ViewModel

    @IntoMap
    @Binds
    @ViewModelKey(AllPhotosViewModel::class)
    fun bindsAllPhotosViewModel(viewModel: AllPhotosViewModel): ViewModel

    @IntoMap
    @Binds
    @ViewModelKey(CollectionViewModel::class)
    fun bindsCollectionViewModel(viewModel: CollectionViewModel): ViewModel
}