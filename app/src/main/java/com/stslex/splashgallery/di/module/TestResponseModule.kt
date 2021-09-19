package com.stslex.splashgallery.di.module

import com.stslex.splashgallery.data.photos.PhotosDataResponse
import com.stslex.splashgallery.domain.PhotosDomainResponse
import dagger.Binds
import dagger.Module
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
@Module
interface TestResponseModule {

    @Binds
    fun bindsPhotoDataResponse(response: PhotosDataResponse.Base): PhotosDataResponse

    @Binds
    fun bindsPhotosDomainResponse(response: PhotosDomainResponse.Base): PhotosDomainResponse
}