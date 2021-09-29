package com.stslex.splashgallery.di.module

import androidx.paging.PagingSource
import com.stslex.splashgallery.data.model.domain.image.ImageModel
import com.stslex.splashgallery.data.photos.AllPhotosService
import com.stslex.splashgallery.data.photos.PhotosPagingSource
import dagger.Module
import dagger.Provides

@Module
class PagingSourceModule {

    @Provides
    fun providePhotosPagingSource(service: AllPhotosService): PagingSource<Int, ImageModel> =
        PhotosPagingSource(service)
}