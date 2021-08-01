package com.stslex.splashgallery.di.module

import com.stslex.splashgallery.data.data_source.RemoteSource
import com.stslex.splashgallery.data.data_source.RemoteSourceImpl
import com.stslex.splashgallery.data.data_source.impl.CollectionSourceImpl
import com.stslex.splashgallery.data.data_source.impl.PhotoSourceImpl
import com.stslex.splashgallery.data.data_source.impl.UserSourceImpl
import com.stslex.splashgallery.data.data_source.interf.CollectionSource
import com.stslex.splashgallery.data.data_source.interf.PhotoSource
import com.stslex.splashgallery.data.data_source.interf.UserSource
import dagger.Binds
import dagger.Module

@Module
interface SourceModule {
    @Binds
    fun bindsRemoteSource(source: RemoteSourceImpl): RemoteSource

    @Binds
    fun bindsPhotoSource(source: PhotoSourceImpl): PhotoSource

    @Binds
    fun bindsCollectionSource(source: CollectionSourceImpl): CollectionSource

    @Binds
    fun bindsUserSource(source: UserSourceImpl): UserSource
}