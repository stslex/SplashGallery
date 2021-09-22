package com.stslex.splashgallery.di.module

import com.stslex.splashgallery.data.collections.CollectionRepository
import com.stslex.splashgallery.data.download.DownloadRepository
import com.stslex.splashgallery.data.photo.PhotoRepository
import com.stslex.splashgallery.data.photos.PhotosRepository
import com.stslex.splashgallery.data.user.UserRepository
import dagger.Binds
import dagger.Module
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
@Module
interface RepositoryModule {
    @Binds
    fun bindsPhotoRepository(repository: PhotoRepository.Base): PhotoRepository

    @Binds
    fun bindsCollectionRepository(repository: CollectionRepository.Base): CollectionRepository

    @Binds
    fun bindsUserRepository(repository: UserRepository.Base): UserRepository

    @Binds
    fun bindsDownloadRepository(repository: DownloadRepository.Base): DownloadRepository

    @Binds
    fun bindsAllPhotosRepository(repository: PhotosRepository.Base): PhotosRepository
}