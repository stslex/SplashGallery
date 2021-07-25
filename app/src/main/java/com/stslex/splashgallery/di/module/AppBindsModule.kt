package com.stslex.splashgallery.di.module

import androidx.lifecycle.ViewModelProvider
import com.stslex.splashgallery.ViewModelFactory
import com.stslex.splashgallery.data.data_source.RemoteSource
import com.stslex.splashgallery.data.data_source.RemoteSourceImpl
import com.stslex.splashgallery.data.repository.ImageRepository
import com.stslex.splashgallery.data.repository.ImageRepositoryImpl
import dagger.Binds
import dagger.Module

@Module
interface AppBindsModule {
    @Binds
    fun bindsRemoteSource(source: RemoteSourceImpl): RemoteSource

    @Binds
    fun bindsImageRepository(repositoryImpl: ImageRepositoryImpl): ImageRepository

    @Binds
    fun bindsViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory
}