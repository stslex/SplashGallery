package com.stslex.splashgallery.di.module

import com.stslex.splashgallery.data.data_source.RemoteSource
import com.stslex.splashgallery.data.data_source.RemoteSourceImpl
import com.stslex.splashgallery.data.repository.ImageRepository
import com.stslex.splashgallery.data.repository.ImageRepositoryImpl
import dagger.Binds
import dagger.Module

@Module(includes = [ViewModelModule::class, ViewModelFactoryModule::class])
interface AppModule {
    @Binds
    fun bindsRemoteSource(source: RemoteSourceImpl): RemoteSource

    @Binds
    fun bindsImageRepository(repositoryImpl: ImageRepositoryImpl): ImageRepository
}