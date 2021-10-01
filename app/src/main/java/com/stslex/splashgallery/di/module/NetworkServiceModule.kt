package com.stslex.splashgallery.di.module

import com.stslex.splashgallery.data.collections.CollectionService
import com.stslex.splashgallery.data.photo.PhotoService
import com.stslex.splashgallery.data.photos.PhotosService
import com.stslex.splashgallery.data.user.UserService
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module(includes = [RetrofitModule::class])
class NetworkServiceModule {
    @Provides
    fun providesPhotoService(retrofit: Retrofit): PhotoService =
        retrofit.create(PhotoService::class.java)

    @Provides
    fun providesCollectionService(retrofit: Retrofit): CollectionService =
        retrofit.create(CollectionService::class.java)

    @Provides
    fun providesUserService(retrofit: Retrofit): UserService =
        retrofit.create(UserService::class.java)

    @Provides
    fun providesAllPhotosService(retrofit: Retrofit): PhotosService =
        retrofit.create(PhotosService::class.java)
}