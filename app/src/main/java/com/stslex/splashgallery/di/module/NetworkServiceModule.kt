package com.stslex.splashgallery.di.module

import com.stslex.splashgallery.data.service.CollectionService
import com.stslex.splashgallery.data.service.PhotoService
import com.stslex.splashgallery.data.service.RetrofitService
import com.stslex.splashgallery.data.service.UserService
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module(includes = [RetrofitModule::class])
class NetworkServiceModule {
    @Provides
    fun providesRetrofitService(retrofit: Retrofit): RetrofitService =
        retrofit.create(RetrofitService::class.java)

    @Provides
    fun providesPhotoService(retrofit: Retrofit): PhotoService =
        retrofit.create(PhotoService::class.java)

    @Provides
    fun providesCollectionService(retrofit: Retrofit): CollectionService =
        retrofit.create(CollectionService::class.java)

    @Provides
    fun providesUserService(retrofit: Retrofit): UserService =
        retrofit.create(UserService::class.java)
}