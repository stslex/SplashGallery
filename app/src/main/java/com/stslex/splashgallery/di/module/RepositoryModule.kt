package com.stslex.splashgallery.di.module

import com.stslex.splashgallery.data.repository.impl.*
import com.stslex.splashgallery.data.repository.interf.*
import dagger.Binds
import dagger.Module
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
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

    @Binds
    fun bindsAllPhotosRepository(repository: AllPhotosRepositoryImpl): AllPhotosRepository
}