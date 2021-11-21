package com.stslex.splashgallery.di.module

import com.stslex.splashgallery.data.photo.PhotoRepository
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
    fun bindsUserRepository(repository: UserRepository.Base): UserRepository
}