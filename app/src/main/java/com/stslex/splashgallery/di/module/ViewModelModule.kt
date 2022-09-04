package com.stslex.splashgallery.di.module

import androidx.lifecycle.ViewModel
import com.stslex.splashgallery.di.key.ViewModelKey
import com.stslex.splashgallery.ui.detail_photo.PhotoDetailsViewModel
import com.stslex.splashgallery.ui.photos.PhotosViewModel
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
    @ViewModelKey(PhotosViewModel::class)
    fun bindsAllPhotosViewModel(viewModel: PhotosViewModel): ViewModel
}