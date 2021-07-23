package com.stslex.splashgallery.di.module

import com.stslex.splashgallery.data.repository.ImageRepository
import com.stslex.splashgallery.data.repository.ImageRepositoryImpl
import dagger.Binds
import dagger.Module

@Module
abstract class RepositoryModule {
    @Binds
    abstract fun bindRepositoryModule(repository: ImageRepositoryImpl): ImageRepository
}
