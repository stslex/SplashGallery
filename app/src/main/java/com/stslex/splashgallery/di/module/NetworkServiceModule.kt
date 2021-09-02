package com.stslex.splashgallery.di.module

import com.stslex.splashgallery.data.service.*
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
    fun providesDownloadService(retrofit: Retrofit): DownloadService =
        retrofit.create(DownloadService::class.java)

    @Provides
    fun providesAllPhotosService(retrofit: Retrofit): AllPhotosService =
        retrofit.create(AllPhotosService::class.java)
}