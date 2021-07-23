package com.stslex.splashgallery.di.module

import com.stslex.splashgallery.data.data_source.RemoteSource
import com.stslex.splashgallery.data.data_source.RemoteSourceImpl
import dagger.Module
import dagger.Provides


@Module
object RemoteSourceModule {
    @Provides
    fun bindRemoteSource(remoteSource: RemoteSource): RemoteSourceImpl = RemoteSourceImpl()
}
