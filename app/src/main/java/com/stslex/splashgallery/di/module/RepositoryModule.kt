package com.stslex.splashgallery.di.module

import com.stslex.splashgallery.data.repository.impl.CollectionRepositoryImpl
import com.stslex.splashgallery.data.repository.impl.DownloadRepositoryImpl
import com.stslex.splashgallery.data.repository.impl.PhotoRepositoryImpl
import com.stslex.splashgallery.data.repository.impl.UserRepositoryImpl
import com.stslex.splashgallery.data.repository.interf.CollectionRepository
import com.stslex.splashgallery.data.repository.interf.DownloadRepository
import com.stslex.splashgallery.data.repository.interf.PhotoRepository
import com.stslex.splashgallery.data.repository.interf.UserRepository
import dagger.Binds
import dagger.Module

@Module
interface RepositoryModule {
    @Binds
    fun bindsPhotoRepository(repository: PhotoRepositoryImpl): PhotoRepository

    @Binds
    fun bindsCollectionRepository(repository: CollectionRepositoryImpl): CollectionRepository

    @Binds
    fun bindsUserRepository(repository: UserRepositoryImpl): UserRepository

    @Binds
    fun bindsDownloadRepository(repository: DownloadRepositoryImpl): DownloadRepository
}