package com.stslex.splashgallery.di.module

import com.stslex.splashgallery.data.photo.PhotoDataResponse
import com.stslex.splashgallery.ui.user.UserUIResponse
import dagger.Binds
import dagger.Module
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
@Module
interface ResponseModule {

    @Binds
    fun bindsPhotoDataResponse(response: PhotoDataResponse.Base): PhotoDataResponse

    @Binds
    fun bindsUserUIResponse(response: UserUIResponse.Base): UserUIResponse
}