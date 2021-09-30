package com.stslex.splashgallery.di.module

import com.stslex.splashgallery.domain.download.DownloadInteractor
import com.stslex.splashgallery.domain.photo.PhotoInteractor
import dagger.Binds
import dagger.Module
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
@Module
interface InteractorModule {

    @Binds
    fun bindsInteractorPhoto(interactor: PhotoInteractor.Base): PhotoInteractor

    @Binds
    fun bindsDownloadInteractor(interactor: DownloadInteractor.Base): DownloadInteractor
}