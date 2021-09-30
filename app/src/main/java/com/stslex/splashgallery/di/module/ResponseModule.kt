package com.stslex.splashgallery.di.module

import com.stslex.splashgallery.data.download.DownloadDataResponse
import com.stslex.splashgallery.data.photo.PhotoDataResponse
import com.stslex.splashgallery.domain.download.DownloadDomainResponse
import com.stslex.splashgallery.domain.photo.PhotoDomainResponse
import com.stslex.splashgallery.ui.detail_photo.DownloadUIResponse
import com.stslex.splashgallery.ui.detail_photo.PhotoUIResponse
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
    fun bindsPhotoDomainResponse(response: PhotoDomainResponse.Base): PhotoDomainResponse

    @Binds
    fun bindsPhotoUIResponse(response: PhotoUIResponse.Base): PhotoUIResponse

    @Binds
    fun bindsUserUIResponse(response: UserUIResponse.Base): UserUIResponse

    @Binds
    fun bindsDownloadDataResponse(response: DownloadDataResponse.Base): DownloadDataResponse

    @Binds
    fun bindsDownloadDomainResponse(response: DownloadDomainResponse.Base): DownloadDomainResponse

    @Binds
    fun bindsDownloadUIResponse(response: DownloadUIResponse.Base): DownloadUIResponse
}