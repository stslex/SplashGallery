package com.stslex.splashgallery.di.module

import com.stslex.splashgallery.data.data_source.RemoteSource
import com.stslex.splashgallery.data.data_source.RemoteSourceImpl
import dagger.Module
import javax.inject.Inject


@Module
object RemoteSourceModule {
    @Inject
    fun bindRemoteSource(remoteSource: RemoteSource): RemoteSourceImpl = RemoteSourceImpl()
}
