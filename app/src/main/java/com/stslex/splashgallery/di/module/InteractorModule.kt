package com.stslex.splashgallery.di.module

import com.stslex.splashgallery.domain.collections.CollectionInteractor
import com.stslex.splashgallery.domain.download.DownloadInteractor
import com.stslex.splashgallery.domain.photo.PhotoInteractor
import com.stslex.splashgallery.domain.photos.PhotosInteractor
import com.stslex.splashgallery.domain.user.UserInteractor
import dagger.Binds
import dagger.Module
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
@Module
interface InteractorModule {

    @Binds
    fun bindsInteractorPhotos(interactor: PhotosInteractor.Base): PhotosInteractor

    @Binds
    fun bindsInteractorPhoto(interactor: PhotoInteractor.Base): PhotoInteractor

    @Binds
    fun bindsCollectionInteractor(interactor: CollectionInteractor.Base): CollectionInteractor

    @Binds
    fun bindsUserInteractor(interactor: UserInteractor.Base): UserInteractor

    @Binds
    fun bindsDownloadInteractor(interactor: DownloadInteractor.Base): DownloadInteractor
}