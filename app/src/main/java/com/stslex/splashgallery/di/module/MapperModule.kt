package com.stslex.splashgallery.di.module

import com.stslex.splashgallery.data.photo.PhotoDataMapper
import com.stslex.splashgallery.data.photos.PhotosDataMapper
import com.stslex.splashgallery.domain.photo.PhotoDomainMapper
import com.stslex.splashgallery.domain.photo.PhotoDomainResult
import com.stslex.splashgallery.domain.photos.PhotosDomainMapper
import com.stslex.splashgallery.domain.photos.PhotosDomainResult
import com.stslex.splashgallery.ui.detail_photo.PhotoUIResult
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

    @Provides
    fun providesPhotoDataMapper(): PhotoDataMapper<PhotoDomainResult> =
        PhotoDataMapper.Base()

    @Provides
    fun providesPhotoDomainMapper(): PhotoDomainMapper<PhotoUIResult> =
        PhotoDomainMapper.Base()

}