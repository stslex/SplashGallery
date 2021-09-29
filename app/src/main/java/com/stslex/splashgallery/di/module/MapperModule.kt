package com.stslex.splashgallery.di.module

import com.stslex.splashgallery.data.download.DownloadDataMapper
import com.stslex.splashgallery.data.photo.PhotoDataMapper
import com.stslex.splashgallery.data.user.UserDataMapper
import com.stslex.splashgallery.domain.download.DownloadDomainMapper
import com.stslex.splashgallery.domain.download.DownloadDomainResult
import com.stslex.splashgallery.domain.photo.PhotoDomainMapper
import com.stslex.splashgallery.domain.photo.PhotoDomainResult
import com.stslex.splashgallery.domain.user.UserDomainMapper
import com.stslex.splashgallery.domain.user.UserDomainResult
import com.stslex.splashgallery.ui.detail_photo.DownloadUIResult
import com.stslex.splashgallery.ui.detail_photo.PhotoUIResult
import com.stslex.splashgallery.ui.user.UserUIResult
import dagger.Module
import dagger.Provides

@Module
class MapperModule {

    @Provides
    fun providesPhotoDataMapper(): PhotoDataMapper<PhotoDomainResult> =
        PhotoDataMapper.Base()

    @Provides
    fun providesPhotoDomainMapper(): PhotoDomainMapper<PhotoUIResult> =
        PhotoDomainMapper.Base()

    @Provides
    fun providesUserDataMapper(): UserDataMapper<UserDomainResult> =
        UserDataMapper.Base()

    @Provides
    fun providesUserDomainMapper(): UserDomainMapper<UserUIResult> =
        UserDomainMapper.Base()

    @Provides
    fun providesDownloadDataMapper(): DownloadDataMapper<DownloadDomainResult> =
        DownloadDataMapper.Base()

    @Provides
    fun providesDownloadDomainMapper(): DownloadDomainMapper<DownloadUIResult> =
        DownloadDomainMapper.Base()

}