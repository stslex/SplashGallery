package com.stslex.splashgallery.di.module

import com.stslex.splashgallery.data.photos.PhotosDataMapper
import com.stslex.splashgallery.domain.PhotosDomainMapper
import com.stslex.splashgallery.domain.PhotosDomainResult
import com.stslex.splashgallery.ui.photos.PhotosUIResult
import dagger.Module
import dagger.Provides

@Module
class MapperModule {

    @Provides
    fun providesPhotosDataMapper(): PhotosDataMapper<PhotosDomainResult> =
        PhotosDataMapper.Base()

    @Provides
    fun providesPhotosDomainMapper(): PhotosDomainMapper<PhotosUIResult> =
        PhotosDomainMapper.Base()

}