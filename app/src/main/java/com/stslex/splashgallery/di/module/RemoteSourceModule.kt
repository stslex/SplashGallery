package com.stslex.splashgallery.di.module

import com.stslex.splashgallery.data.data_source.RemoteSource
import com.stslex.splashgallery.data.data_source.RemoteSourceImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RemoteSourceModule {
    @Singleton
    @Provides
    fun bindRemoteSource(): RemoteSource = RemoteSourceImpl()
}
