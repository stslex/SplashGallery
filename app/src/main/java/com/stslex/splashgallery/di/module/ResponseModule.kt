package com.stslex.splashgallery.di.module

import com.stslex.splashgallery.data.core.CreateResponse
import com.stslex.splashgallery.data.model.domain.DownloadModel
import com.stslex.splashgallery.data.model.domain.collection.CollectionModel
import com.stslex.splashgallery.data.model.domain.image.ImageModel
import com.stslex.splashgallery.data.model.domain.user.UserModel
import com.stslex.splashgallery.data.model.remote.RemoteCollectionModel
import com.stslex.splashgallery.data.model.remote.RemoteDownloadModel
import com.stslex.splashgallery.data.model.remote.RemoteImageModel
import com.stslex.splashgallery.data.model.remote.RemoteUserModel
import com.stslex.splashgallery.mapper.CollectionMapper
import com.stslex.splashgallery.mapper.DownloadMapper
import com.stslex.splashgallery.mapper.ImageMapper
import com.stslex.splashgallery.mapper.UserMapper
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
@Module
class ResponseModule {

    @Provides
    fun provideResponseUser(): CreateResponse<RemoteUserModel, UserModel> =
        CreateResponse.Base(UserMapper())

    @Provides
    fun provideResponseCollection(): CreateResponse<RemoteCollectionModel, CollectionModel> =
        CreateResponse.Base(CollectionMapper())

    @Provides
    fun provideResponseImage(): CreateResponse<RemoteImageModel, ImageModel> =
        CreateResponse.Base(ImageMapper())

    @Provides
    fun provideResponseDownload(): CreateResponse<RemoteDownloadModel, DownloadModel> =
        CreateResponse.Base(DownloadMapper())
}