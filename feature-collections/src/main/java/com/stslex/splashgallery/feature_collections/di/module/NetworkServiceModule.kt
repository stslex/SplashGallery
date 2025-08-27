package com.stslex.splashgallery.feature_collections.di.module

import com.stslex.splashgallery.feature_collections.data.CollectionService
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module(includes = [RetrofitModule::class])
class NetworkServiceModule {

    @Provides
    fun providesCollectionService(retrofit: Retrofit): CollectionService =
        retrofit.create(CollectionService::class.java)
}