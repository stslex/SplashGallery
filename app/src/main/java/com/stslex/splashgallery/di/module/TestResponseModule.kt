package com.stslex.splashgallery.di.module

import com.stslex.splashgallery.data.photo.PhotoDataResponse
import com.stslex.splashgallery.data.photos.PhotosDataResponse
import com.stslex.splashgallery.domain.photo.PhotoDomainResponse
import com.stslex.splashgallery.domain.photos.PhotosDomainResponse
import com.stslex.splashgallery.ui.detail_photo.PhotoUIResponse
import com.stslex.splashgallery.ui.photos.PhotosUIResponse
import dagger.Binds
import dagger.Module
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
@Module
interface TestResponseModule {

    @Binds
    fun bindsPhotosDataResponse(response: PhotosDataResponse.Base): PhotosDataResponse

    @Binds
    fun bindsPhotosDomainResponse(response: PhotosDomainResponse.Base): PhotosDomainResponse

    @Binds
    fun bindsPhotosUIResponse(response: PhotosUIResponse.Base): PhotosUIResponse

    @Binds
    fun bindsPhotoDataResponse(response: PhotoDataResponse.Base): PhotoDataResponse

    @Binds
    fun bindsPhotoDomainResponse(response: PhotoDomainResponse.Base): PhotoDomainResponse

    @Binds
    fun bindsPhotoUIResponse(response: PhotoUIResponse.Base): PhotosUIResponse
}