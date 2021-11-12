package com.stslex.splashgallery.di.module

import com.stslex.splashgallery.data.photo.DownloadDataMapper
import com.stslex.splashgallery.data.photo.PhotoDataMapper
import com.stslex.splashgallery.data.user.UserDataMapper
import dagger.Binds
import dagger.Module

@Module
interface MappersModule {

    @Binds
    fun bindsPhotoDataMapper(mapper: PhotoDataMapper.Base): PhotoDataMapper

    @Binds
    fun bindsUserDataMapper(mapper: UserDataMapper.Base): UserDataMapper

    @Binds
    fun bindsDownloadDataMapper(mapper: DownloadDataMapper.Base): DownloadDataMapper
}