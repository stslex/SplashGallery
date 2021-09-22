package com.stslex.splashgallery.di.module

import com.stslex.splashgallery.data.collections.CollectionDataResponse
import com.stslex.splashgallery.data.download.DownloadDataResponse
import com.stslex.splashgallery.data.photo.PhotoDataResponse
import com.stslex.splashgallery.data.photos.PhotosDataResponse
import com.stslex.splashgallery.data.user.UserDataResponse
import com.stslex.splashgallery.domain.collections.CollectionDomainResponse
import com.stslex.splashgallery.domain.download.DownloadDomainResponse
import com.stslex.splashgallery.domain.photo.PhotoDomainResponse
import com.stslex.splashgallery.domain.photos.PhotosDomainResponse
import com.stslex.splashgallery.domain.user.UserDomainResponse
import com.stslex.splashgallery.ui.collections.CollectionUIResponse
import com.stslex.splashgallery.ui.detail_photo.DownloadUIResponse
import com.stslex.splashgallery.ui.detail_photo.PhotoUIResponse
import com.stslex.splashgallery.ui.photos.PhotosUIResponse
import com.stslex.splashgallery.ui.user.UserUIResponse
import dagger.Binds
import dagger.Module
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
@Module
interface ResponseModule {

    @Binds
    fun bindsPhotosDataResponse(response: PhotosDataResponse.Base): PhotosDataResponse

    @Binds
    fun bindsPhotosDomainResponse(response: PhotosDomainResponse.Base): PhotosDomainResponse

    @Binds
    fun bindsPhotosUIResponse(response: PhotosUIResponse.Base): PhotosUIResponse

    @Binds
    fun bindsPhotoDataResponse(response: PhotoDataResponse.Base): PhotoDataResponse

    @Binds
    fun bindsPhotoDomainResponse(response: PhotoDomainResponse.Base): PhotoDomainResponse

    @Binds
    fun bindsPhotoUIResponse(response: PhotoUIResponse.Base): PhotoUIResponse

    @Binds
    fun bindsCollectionDataResponse(response: CollectionDataResponse.Base): CollectionDataResponse

    @Binds
    fun bindsCollectionDomainResponse(response: CollectionDomainResponse.Base): CollectionDomainResponse

    @Binds
    fun bindsCollectionUIResponse(response: CollectionUIResponse.Base): CollectionUIResponse

    @Binds
    fun bindsUserDataResponse(response: UserDataResponse.Base): UserDataResponse

    @Binds
    fun bindsUserDomainResponse(response: UserDomainResponse.Base): UserDomainResponse

    @Binds
    fun bindsUserUIResponse(response: UserUIResponse.Base): UserUIResponse

    @Binds
    fun bindsDownloadDataResponse(response: DownloadDataResponse.Base): DownloadDataResponse

    @Binds
    fun bindsDownloadDomainResponse(response: DownloadDomainResponse.Base): DownloadDomainResponse

    @Binds
    fun bindsDownloadUIResponse(response: DownloadUIResponse.Base): DownloadUIResponse
}