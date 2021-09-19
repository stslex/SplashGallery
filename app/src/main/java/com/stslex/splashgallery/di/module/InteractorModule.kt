package com.stslex.splashgallery.di.module

import com.stslex.splashgallery.domain.PhotosDomain
import com.stslex.splashgallery.domain.PhotosInteractor
import dagger.Binds
import dagger.Module
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
@Module
interface InteractorModule {

    @Binds
    fun bindsInteractorPhotos(interactor: PhotosInteractor.Base): PhotosInteractor<List<PhotosDomain>>
}